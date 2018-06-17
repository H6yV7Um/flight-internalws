package com.ctrip.ibu.flight.internalws.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * SpringContext Holder
 * Created by kyxie on 2017/11/16.
 */
@Component("springContextHolder")
public class SpringContextHolder implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public Object getBean(String beanName) throws BeansException {
        return applicationContext.getBean(beanName);
    }

    public Object getBean(String beanName, Class requiredType) throws BeansException {
        return applicationContext.getBean(beanName, requiredType);
    }

    public boolean containsBean(String beanName) {
        return applicationContext.containsBean(beanName);
    }

    public boolean isSingleton(String beanName) throws NoSuchBeanDefinitionException {
        return applicationContext.isSingleton(beanName);
    }

    public Class getType(String beanName) throws NoSuchBeanDefinitionException {
        return applicationContext.getType(beanName);
    }

    public String[] getAliases(String beanName) throws NoSuchBeanDefinitionException {
        return applicationContext.getAliases(beanName);
    }

    public String[] getBeanNamesForAnnotation(Class<? extends Annotation> anno) throws NoSuchBeanDefinitionException{
        return applicationContext.getBeanNamesForAnnotation(anno);
    }
}
