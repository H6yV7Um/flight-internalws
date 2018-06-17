package com.ctrip.ibu.flight.internalws.models.exception.common;

/**
 * Created by kyxie on 2017/7/15.
 */
public class TemplateRenderException extends Exception {

    private String description;

    private String templateName;

    public TemplateRenderException(){
        super();
    }

    public TemplateRenderException(String desc){
        this.description = desc;
    }

    public TemplateRenderException(String templateName, String desc){
        super(desc);
        this.templateName = templateName;
        this.description = desc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
}
