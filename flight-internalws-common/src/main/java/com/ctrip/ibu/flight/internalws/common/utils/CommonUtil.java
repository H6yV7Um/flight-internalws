package com.ctrip.ibu.flight.internalws.common.utils;

import com.ctrip.framework.clogging.agent.log.ILog;
import com.ctrip.framework.clogging.agent.log.LogManager;
import com.ctrip.ibu.flight.internalws.contract.commontypes.EmailTemplateType;
import com.ctrip.ibu.flight.internalws.contract.commontypes.LanguageType;
import com.ctrip.ibu.flight.internalws.models.constant.LogConst;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Common帮助类
 * Created by kyxie on 2017/8/7.
 */
public final class CommonUtil {

    private final static ILog CLOG = LogManager.getLogger(CommonUtil.class);

    private final static String NUMVERIC_REGEX = "[0-9]*";

    /**
     * 判断一个字符串是否为数字
     */
    public static boolean isNumberic(String str) {
        Pattern pattern = Pattern.compile(NUMVERIC_REGEX);
        return pattern.matcher(str).matches();
    }

    /**
     * 获取邮件发送ID
     */
    public static String getSendId(Long orderId, EmailTemplateType emailTemplateType, LanguageType languageType) {
        int hashCode = UUID.randomUUID().toString().hashCode();
        if (hashCode < 0) {
            hashCode = -hashCode;
        }

        return String.format("%d_%s_%s_%s", orderId, emailTemplateType.toString(), languageType.toString(), hashCode);
    }

    /**
     * 转换List
     *
     * @param origList 需要转换的List
     * @param func     列表的每个对象的转换方法
     * @return 根据func指定的单个对象转换方法转换List
     */
    public static <T1, T2> List<T2> convertList(List<T1> origList, Function<T1, T2> func) {
        if (origList == null || origList.isEmpty() || func == null) {
            return null;
        }

        List<T2> result = new ArrayList<>();
        origList.forEach(origVal -> {
            result.add(func.apply(origVal));
        });

        return result;
    }

    /**
     * 判断Map中是否存在指定的Key
     *
     * @param mapSet     源Map
     * @param key        指定的Key
     * @param ignoreCase 是否忽略大小写
     * @return 是否存在
     */
    public static <T> boolean containsKey(Map<String, T> mapSet, String key, Boolean ignoreCase) {
        if (mapSet == null || mapSet.isEmpty()) {
            return false;
        }

        boolean exist = false;

        if (key == null) {
            if (mapSet.containsKey(null)) {
                exist = true;
            }
        } else {
            for (Map.Entry<String, T> entry : mapSet.entrySet()) {
                if (ignoreCase) {
                    if (key.equalsIgnoreCase(entry.getKey())) {
                        exist = true;
                        break;
                    }
                } else {
                    if (key.equals(entry.getKey())) {
                        exist = true;
                        break;
                    }
                }
            }
        }
        return exist;
    }

    /**
     * 获取指定Key的数据
     */
    public static <T> T getValue(Map<String, T> mapSet, String key, Boolean ignoreCase) {
        if (mapSet == null || mapSet.isEmpty()) {
            return null;
        }

        for (Map.Entry<String, T> entry : mapSet.entrySet()) {
            if (key == null) {
                if (entry.getKey() == null) {
                    return entry.getValue();
                }
            } else {
                if (ignoreCase) {
                    if (key.equalsIgnoreCase(entry.getKey())) {
                        return entry.getValue();
                    }
                } else {
                    if (key.equals(entry.getKey())) {
                        return entry.getValue();
                    }
                }
            }
        }

        return null;
    }

    /**
     * List转换为数组
     * @param origList 原始List
     * @param elemType List中元素的类型
     * @return 指定元素类型的数组
     * */
    public static <T> T[] convertListToArray(List<T> origList,Class<T> elemType){
        if (origList == null){
            return null;
        }

        int size = origList.size();
        T[] array = (T[]) Array.newInstance(elemType,size);
        for (int i = 0;i < size;i++){
            array[i] = (T) origList.get(i);
        }
        return array;
    }

    /**
     * 获取操作系统名称
     * */
    public static String getOsName(){
        return System.getProperty("os.name");
    }

    /**
     * 筛选列表元素
     * @param origList 原始列表
     * @param condition 筛选条件
     * */
    public static <T> List<T> selectList(List<T> origList, @NotNull Predicate<T> condition){
        if (origList == null || origList.size() == 0){
            return null;
        }

        return origList.stream().filter(condition).collect(Collectors.toList());
    }

    /**
     * 重新设置Null的Field为空字符串
     * @param obj 需要重置属性的对象
     * */
    public static <T> void resetNullField(T obj){
        if (obj != null && obj.getClass() != null && obj.getClass().getDeclaredMethods() != null && obj.getClass().getDeclaredMethods().length > 0){

            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields){
                try {
                    if (!field.isAccessible()){
                        field.setAccessible(true);
                    }
                    if (String.class.equals(field.getType()) && field.get(obj) == null){
                        field.set(obj,"");
                    }
                } catch (Exception e) {
                    CLOG.warn(LogConst.Clog.LogTitle.SYSTEM_MONITOR,e);
                }

            }

        }
    }
}
