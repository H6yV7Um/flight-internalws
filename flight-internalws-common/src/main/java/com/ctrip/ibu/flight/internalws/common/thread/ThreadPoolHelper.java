package com.ctrip.ibu.flight.internalws.common.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池帮助类
 * Created by kyxie on 2017/8/2.
 */
public class ThreadPoolHelper {

    private static ThreadPoolHelper _instance = null;

    private ExecutorService executorService = null;

    private ThreadPoolHelper(){
        executorService = Executors.newCachedThreadPool();
    }

    /**
     * 获取帮助类实例
     * */
    public synchronized static ThreadPoolHelper getInstance(){
        if (_instance == null){
            _instance = new ThreadPoolHelper();
        }

        return _instance;
    }

    /**
     * 执行任务
     * @param task 任务
     * */
    public void execute(Runnable task){
        executorService.execute(task);
    }
}
