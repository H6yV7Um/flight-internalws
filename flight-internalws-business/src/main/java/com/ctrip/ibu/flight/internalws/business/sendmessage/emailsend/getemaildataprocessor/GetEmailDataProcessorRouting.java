package com.ctrip.ibu.flight.internalws.business.sendmessage.emailsend.getemaildataprocessor;

import com.ctrip.framework.clogging.agent.log.ILog;
import com.ctrip.framework.clogging.agent.log.LogManager;
import com.ctrip.ibu.flight.internalws.common.components.Checker;
import com.ctrip.ibu.flight.internalws.common.log.LoggerHelper;
import com.ctrip.ibu.flight.internalws.common.utils.SpringContextHolder;
import com.ctrip.ibu.flight.internalws.models.annotation.GetEmailDataProcessor;
import com.ctrip.ibu.flight.internalws.contract.commontypes.EmailTemplateType;
import com.ctrip.ibu.flight.internalws.models.errorcode.emailsend.GetEmailDataImplResultCode;
import com.ctrip.ibu.flight.internalws.models.exception.emailsend.GetEmailDataImplException;
import com.ctrip.ibu.flight.internalws.models.log.IndexedLogTag;
import com.ctrip.ibu.flight.internalws.models.log.LogLevel;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.*;

/**
 * 获取邮件数据真正的处理类Routing
 * Create by kyxie on 2018/4/15 16:59
 */
@Component
public class GetEmailDataProcessorRouting {

    private final static ILog CLOG = LogManager.getLogger(GetEmailDataProcessorRouting.class);

    private final static String LOG_TITLE = "GetEmailDataProcessorRouting";

    private SpringContextHolder contextHolder;

    private Map<EmailTemplateType,EmailDataProcessorConfig> templateTypeAndProcessorMapper = new HashMap<>();

    @Inject
    public GetEmailDataProcessorRouting(SpringContextHolder contextHolder){
        this.contextHolder = contextHolder;
    }

    /**
     * 根据模板类型获取实现类
     * @param emailTemplateType 邮件模板类型
     * @return 模板数据实现类
     * */
    public GetEmailDataProcessBase getImplProcessor(EmailTemplateType emailTemplateType) throws GetEmailDataImplException{

        EmailDataProcessorConfig processor = templateTypeAndProcessorMapper.get(emailTemplateType);

        Checker.checkWithThrowable(processor == null || processor.processedEmailTemplates == null || processor.processedEmailTemplates.size() == 0,
                new GetEmailDataImplException(emailTemplateType, GetEmailDataImplResultCode.NO_IMPLEMENT,String.format("邮件模板%s缺少处理类实现(请查看是否没有给模板定义处理类，或者顶一个多个处理类)",String.valueOf(emailTemplateType))),
                        () -> {
                            LoggerHelper.addIndexedLogTag(IndexedLogTag.LogLevel, LogLevel.FATAL.toString());
                            LoggerHelper.appendResponseContent(String.format("邮件模板%s缺少处理类实现(请查看是否没有给模板定义处理类，或者定义了多个处理类)",String.valueOf(emailTemplateType)));
                        });

        return (GetEmailDataProcessBase) contextHolder.getBean(processor.beanName);
    }

    /**
     * 初始化
     * */
    @PostConstruct
    private void initMappings(){

        //获取指定注解GetEmailDataProcessor的Bean
        String[] beanNames = contextHolder.getBeanNamesForAnnotation(GetEmailDataProcessor.class);

        if (beanNames != null && beanNames.length > 0){
            for (String beanName : beanNames){
                try {
                    Object bean = contextHolder.getBean(beanName);
                    GetEmailDataProcessor beanAnno = bean.getClass().getAnnotation(GetEmailDataProcessor.class);
                    EmailTemplateType[] processedTmpls = beanAnno.processedEmailTypes();
                    if (processedTmpls.length > 0){
                        EmailDataProcessorConfig processor = new EmailDataProcessorConfig();
                        processor.setBeanName(beanName);
                        processor.setProcessorName(beanAnno.name());
                        processor.processedEmailTemplates = new ArrayList<>();

                        //已经存在处理类映射的模板类型
                        List<EmailTemplateType> badTmpls = new ArrayList<>();

                        for (EmailTemplateType processedTmpl : processedTmpls){
                            if (templateTypeAndProcessorMapper.containsKey(processedTmpl)){
                                badTmpls.add(processedTmpl);

                                //删除处理类对该模板类型的映射
                                templateTypeAndProcessorMapper.get(processedTmpl).processedEmailTemplates.remove(processedTmpl);

                                //删除模板类型与该处理类的映射
                                templateTypeAndProcessorMapper.remove(processedTmpl);
                                break;
                            }
                            processor.processedEmailTemplates.add(processedTmpl);
                        }

                        for (EmailTemplateType processedTmpl : processedTmpls){
                            if (!badTmpls.contains(processedTmpl)){
                                templateTypeAndProcessorMapper.put(processedTmpl,processor);
                            }
                        }
                    }
                } catch (NoSuchBeanDefinitionException e){
                    CLOG.error(LOG_TITLE,e);
                }
            }
        }
    }


    /**
     * 获取邮件数据处理类配置类
     * */
    class EmailDataProcessorConfig{

        /**
         * Bean名称
         * */
        private String beanName;

        /**
         * Processor名称描述
         * */
        private String processorName;

        /**
         * 该处理类可以处理的邮件模板类型
         * */
        private List<EmailTemplateType> processedEmailTemplates;

        public String getBeanName() {
            return beanName;
        }

        public void setBeanName(String beanName) {
            this.beanName = beanName;
        }

        public String getProcessorName() {
            return processorName;
        }

        public void setProcessorName(String processorName) {
            this.processorName = processorName;
        }

        public List<EmailTemplateType> getProcessedEmailTemplates() {
            return processedEmailTemplates;
        }

        public void setProcessedEmailTemplates(List<EmailTemplateType> processedEmailTemplates) {
            this.processedEmailTemplates = processedEmailTemplates;
        }
    }
}
