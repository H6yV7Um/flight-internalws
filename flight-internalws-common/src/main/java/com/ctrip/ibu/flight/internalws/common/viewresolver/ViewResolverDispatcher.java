package com.ctrip.ibu.flight.internalws.common.viewresolver;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * 自定义视图解析器
 * Created by kyxie on 2017/8/28.
 */
public class ViewResolverDispatcher implements ViewResolver{

    //ViewResolver集合
    private Map<Set<String>,ViewResolver> viewResolverMap = new HashMap<>();

    private ViewResolver defaultViewResolver;

    /**
     * 解析视图
     * @param viewName 视图名
     * @param locale Locale
     * */
    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {

        for (Map.Entry<Set<String>,ViewResolver> map : viewResolverMap.entrySet()){
            //模板文件后缀
            Set<String> suffixs = map.getKey();

            for (String suffix : suffixs){
                if (viewName.endsWith(suffix)){
                    ViewResolver viewResolver = map.getValue();
                    if (viewResolver != null){
                        //去掉后缀
                        return viewResolver.resolveViewName(viewName.replace(suffix,""),locale);
                    }
                }
            }
        }

        return null;
    }

    public Map<Set<String>, ViewResolver> getViewResolverMap() {
        return viewResolverMap;
    }

    public void setViewResolverMap(Map<Set<String>, ViewResolver> viewResolverMap) {
        this.viewResolverMap = viewResolverMap;
    }

    public ViewResolver getDefaultViewResolver() {
        return defaultViewResolver;
    }

    public void setDefaultViewResolver(ViewResolver defaultViewResolver) {
        this.defaultViewResolver = defaultViewResolver;
    }
}
