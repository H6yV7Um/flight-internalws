package com.ctrip.ibu.flight.internalws.models.message;

/**
 * 资源文件路径实体
 * Create by kyxie on 2018/2/1 18:39
 */
public class ResourcePathModel {

    //商标路径
    private String trademarkLogoPath;

    //iata图片路径
    private String iataLogoPath;

    //印章路径
    private String stampLogoPath;

    public String getTrademarkLogoPath() {
        return trademarkLogoPath;
    }

    public void setTrademarkLogoPath(String trademarkLogoPath) {
        this.trademarkLogoPath = trademarkLogoPath;
    }

    public String getIataLogoPath() {
        return iataLogoPath;
    }

    public void setIataLogoPath(String iataLogoPath) {
        this.iataLogoPath = iataLogoPath;
    }

    public String getStampLogoPath() {
        return stampLogoPath;
    }

    public void setStampLogoPath(String stampLogoPath) {
        this.stampLogoPath = stampLogoPath;
    }
}
