package com.ctrip.ibu.flight.internalws.common.config;

import java.util.EventObject;
import java.util.Map;

/**
 * QConfig普通文件改变事件
 * Created by kyxie on 2017/7/22.
 */
public class NormalFileChangedEvent extends EventObject {

    //普通的文件，Key:文件名，Value:文件内容
    private Map<String,String> changedFileContent;

    public NormalFileChangedEvent(Object source, Map<String,String> changedFileContent){
        super(source);
        this.changedFileContent = changedFileContent;
    }

    public Map<String, String> getChangedFileContent() {
        return changedFileContent;
    }
}
