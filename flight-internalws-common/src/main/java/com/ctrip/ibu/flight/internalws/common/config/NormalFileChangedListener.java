package com.ctrip.ibu.flight.internalws.common.config;

import java.util.EventListener;

/**
 * QConfig普通文件内容改变监听器
 * Created by kyxie on 2017/7/22.
 */
public interface NormalFileChangedListener extends EventListener {

    /**
     * 文件内容改变时的监听器
     * */
    void onFileContentChanged(NormalFileChangedEvent event);
}
