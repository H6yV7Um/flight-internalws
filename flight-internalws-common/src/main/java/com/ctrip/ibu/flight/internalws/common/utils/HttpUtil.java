package com.ctrip.ibu.flight.internalws.common.utils;

import com.ctrip.framework.foundation.Foundation;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Http相关工具类
 * Created by kyxie on 2017/8/30.
 */
public final class HttpUtil {

    private static final Gson gson = new Gson();

    private HttpUtil(){
        throw new IllegalArgumentException("Static Class Init Forbidden");
    }

    public static <T extends Object> String sendPost(String url,String requestParam, T request){
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);

        List<NameValuePair> formParams = new ArrayList<>();
        formParams.add(new BasicNameValuePair(requestParam,gson.toJson(request)));

        CloseableHttpResponse httpResponse = null;

        try{
            StringEntity stringEntity = new StringEntity(gson.toJson(request),"UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);

            httpResponse = httpClient.execute(httpPost);

            HttpEntity entity = httpResponse.getEntity();

            if (entity != null){
                return EntityUtils.toString(entity);
            }
        } catch (UnsupportedEncodingException e) {
            System.out.println("不支持的编码类型:" + e.getLocalizedMessage());
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpResponse != null){
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpClient != null){
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    /**
     * 获取本机IP地址
     * */
    public static String getLocaleHostAddress(){
        return Foundation.net().getHostAddress();
    }
}
