package com.ctrip.ibu.flight.internalws.common.template;


import com.ctrip.ibu.flight.internalws.common.config.Config;
import com.ctrip.ibu.flight.internalws.common.config.NormalFileChangedListener;
import com.ctrip.ibu.flight.internalws.common.config.NormalFileChangedEvent;
import com.ctrip.ibu.flight.internalws.models.constant.BeanConst;
import freemarker.cache.StringTemplateLoader;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

/**
 * 自定义FreeMarker模板加载器
 * Created by kyxie on 2017/7/22.
 */
@Component(BeanConst.BEANNAME_FREEMARKERSTRINGTMPLLOADER)
public class CustomFreemarkerTemplateLoader extends StringTemplateLoader {

    private Config config;

    //需要缓存的模板文件名
    private List<String> cachedTemplateNames;

    @Inject
    public CustomFreemarkerTemplateLoader(@Named("config") Config config){
        this.config = config;
        this.cachedTemplateNames = config.getApplicationInfo().getEmailConfigInfo().getCachedTemplatesNameList();

        initTemplateCache();//初始化模板缓存

        //设置监听器，当配置改变时更新缓存
        this.config.addEventListener(new NormalFileChangedListener() {
            @Override
            public void onFileContentChanged(NormalFileChangedEvent event) {
                Map<String,String> changedConfig = event.getChangedFileContent();
                updateTemplateCache(changedConfig);
            }
        });
    }

    /**
     * 初始化模板缓存
     * */
    private void initTemplateCache(){

        //从QConfig中获取模板文件内容写入缓存
        if (this.cachedTemplateNames != null && !this.cachedTemplateNames.isEmpty()){
            for (String templateName : cachedTemplateNames){
                String templateContent = config.getNormalConfig(templateName);
                if (isExistTemplateCache(templateName)){
                    super.removeTemplate(templateName);
                }
                //内容不为NULL才加入
                if (templateContent != null){
                    super.putTemplate(templateName,templateContent);
                }
            }
        }
    }

    /**
     * 更新模板缓存
     * @param newestTemplateContent 需要更新的模板
     * */
    private synchronized void updateTemplateCache(Map<String,String> newestTemplateContent){

        if (newestTemplateContent != null && !newestTemplateContent.isEmpty()){

            for (Map.Entry<String,String> entry : newestTemplateContent.entrySet()){
                String tmplKey = entry.getKey();
                String tmplValue = entry.getValue();
                if (cachedTemplateNames.contains(tmplKey)){
                    if (isExistTemplateCache(tmplKey)){
                        super.removeTemplate(tmplKey);
                    }
                    if (tmplValue != null){
                        super.putTemplate(tmplKey,newestTemplateContent.get(tmplKey));
                    }
                }
            }
        }
    }

    /**
     * 判断是否已经存在模板缓存
     * @param templateName 模板名
     * */
    private boolean isExistTemplateCache(String templateName){
        Object templateContent = super.findTemplateSource(templateName);
        if (templateContent == null){
            return false;
        }
        return true;
    }
}
