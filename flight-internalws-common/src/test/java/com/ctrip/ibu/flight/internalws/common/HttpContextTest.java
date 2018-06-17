package com.ctrip.ibu.flight.internalws.common;

import com.ctrip.ibu.flight.internalws.common.components.HttpContext;
import org.junit.Test;

/**
 * Create by kyxie on 2018/1/22 17:47
 */
public class HttpContextTest {

    @Test
    public void testServiceContext(){
        HttpContext.add("count","100");
        for (int i = 0;i < 10;i++){
            new Thread(() -> {
                HttpContext.update("count",(int)HttpContext.get("count") + 1);
                print("SUB_THREAD");
            }).start();
        }
    }

    private void print(String threadName){
        System.out.println(String.format("Thread methodDesc is [%s], Thread id is [%s] and count is [%s]", threadName, Thread.currentThread().getId(), HttpContext.get("count")));
    }
}
