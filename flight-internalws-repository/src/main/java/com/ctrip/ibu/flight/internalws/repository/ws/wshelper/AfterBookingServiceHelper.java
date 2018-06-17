package com.ctrip.ibu.flight.internalws.repository.ws.wshelper;

import com.ctrip.framework.foundation.Foundation;
import com.ctrip.ibu.flight.afterbooking.base.RequestHead;
import com.ctrip.ibu.flight.afterbooking.base.ResponseHead;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * AfterBooking接口帮助类
 * author : kyxie
 * date : 2018/5/2 11:52
 */
@Component
public class AfterBookingServiceHelper {

    private final static Locale DEFAULT_LOCALE = Locale.US;

    /**
     * 获取默认Head
     * */
    public RequestHead getDefaultRequestHead(){
        RequestHead requestHead = new RequestHead();
        requestHead.setIP(Foundation.net().getHostAddress());
        requestHead.setLocale(DEFAULT_LOCALE.toString());
        requestHead.setSource("ANDROID");
        requestHead.setVersion("601.000");
        return requestHead;
    }

    /**
     * 根据传入的Head获取RequestHead
     * @param head 传入的Head
     * */
    public RequestHead getRequestHead(com.ctrip.ibu.flight.internalws.models.common.RequestHead head){

        RequestHead requestHead = getDefaultRequestHead();
        if (head != null){
            requestHead.setUID(head.getUid());
            requestHead.setCurrency(String.valueOf(head.getCurrency()));
            if (head.getLanguage() != null){
                requestHead.setLocale(head.getLanguage().toString());
            }
        }
        return requestHead;
    }

    /**
     * 获取ResponseHead中的错误信息
     * @param responseHead 响应头
     * */
    public String getResponseHeadMsg(ResponseHead responseHead){
        if (responseHead == null){
            return "";
        }

        return String.format("ErrorCode : %s,ErrorMsg : %s,ShowErrorMsg : %s",responseHead.errorCode,responseHead.errorMessage,responseHead.showErrorMessage);
    }
}
