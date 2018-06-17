package com.ctrip.ibu.flight.internalws.common.utils;

import com.ctrip.framework.clogging.agent.log.ILog;
import com.ctrip.framework.clogging.agent.log.LogManager;
import com.ctrip.ibu.flight.internalws.models.constant.LogConst;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.SoftReferenceObjectPool;
import org.springframework.util.ResourceUtils;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.IOException;

/**
 * ITextRenderer池管理器
 * @author : kyxie
 * createtime : 2018/5/28 11:56
 */
public class ITextRendererPoolManager extends SoftReferenceObjectPool<ITextRenderer> {

    private final static ILog CLOG = LogManager.getLogger(ITextRendererPoolManager.class);

    private static final Object _lock = new Object();

    private static volatile ITextRendererPoolManager _instance = null;

    /**
     * 私有化构造函数
     * @param factory ITextRenderer工厂
     * */
    private ITextRendererPoolManager(PooledObjectFactory<ITextRenderer> factory) {
        super(factory);
    }

    /**
     * 获取ITextRendererPoolManager实例
     * */
    public static ITextRendererPoolManager getInstance(){
        if (_instance == null){
            synchronized (_lock){
                if (_instance == null){
                    _instance = new ITextRendererPoolManager(new PooledITextRendererFactory());
                }
            }
        }
        return _instance;
    }

    /**
     * 池化的ITextRenderer工厂
     * */
    static class PooledITextRendererFactory extends BasePooledObjectFactory<ITextRenderer> {

        @Override
        public ITextRenderer create() throws Exception {

            ITextRenderer instance = new ITextRenderer();
            ITextFontResolver fontResolver = instance.getFontResolver();

            //设置字体
            try {
                fontResolver.addFont(ResourceUtils.getURL("classpath:/fonts/simsun.ttc").getPath(), BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
                fontResolver.addFont(ResourceUtils.getURL("classpath:/fonts/ARIALUNI.TTF").getPath(),BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
                fontResolver.addFont(ResourceUtils.getURL("classpath:/fonts/arial.ttf").getPath(),BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
                fontResolver.addFont(ResourceUtils.getURL("classpath:/fonts/simhei.ttf").getPath(),BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
                fontResolver.addFont(ResourceUtils.getURL("classpath:/fonts/msgothic.ttc").getPath(),BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
                fontResolver.addFont(ResourceUtils.getURL("classpath:/fonts/gulim.ttc").getPath(),BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
                fontResolver.addFont(ResourceUtils.getURL("classpath:/fonts/batang.ttc").getPath(),BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
                fontResolver.addFont(ResourceUtils.getURL("classpath:/fonts/malgun.ttf").getPath(),BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
                fontResolver.addFont(ResourceUtils.getURL("classpath:/fonts/angsa.ttf").getPath(),BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
            } catch (DocumentException | IOException e) {
                CLOG.error(LogConst.CLOGTITLE_SYSTEMMONITOR,e);
            }

            return instance;
        }

        @Override
        public PooledObject<ITextRenderer> wrap(ITextRenderer rawInstance) {
            return new DefaultPooledObject<>(rawInstance);
        }
    }
}
