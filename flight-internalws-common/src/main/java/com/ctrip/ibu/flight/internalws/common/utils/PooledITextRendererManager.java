package com.ctrip.ibu.flight.internalws.common.utils;

import com.ctrip.framework.clogging.agent.log.ILog;
import com.ctrip.framework.clogging.agent.log.LogManager;
import com.ctrip.ibu.flight.internalws.models.constant.LogConst;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * ITextRenderer池管理器
 * 建议使用ITextRendererPoolManager
 * Create by kyxie on 2018/3/2 14:45
 * @see ITextRendererPoolManager
 */
@Deprecated
@Component
public class PooledITextRendererManager {

    private static final ILog LOGGER = LogManager.getLogger(PooledITextRendererManager.class);

    private static final int DEFAULT_INIT_ITEXTRENDERER_SIZE = 10;

    private static final int DEFAULT_MAX_ITEXTRENDERER_SIZE = 100;

    private static final int DEFAULT_INCREMENT_ITEXTRENDERER_INSTANCE = 10;

    private static final int SLEEPINTERVALMILLS = 30;

    private Set<PooledITextRenderer> iTextRendererPool = null;

    public PooledITextRendererManager(){
        createPool();
    }

    /**
     * 创建ITextRender池
     * */
    private synchronized void createPool(){
        if (this.iTextRendererPool != null && !this.iTextRendererPool.isEmpty()){
            return;
        }

        this.iTextRendererPool = new HashSet<>();
        this.createITextRenderPool(this.DEFAULT_INIT_ITEXTRENDERER_SIZE);
    }

    /**
     * 创建指定大小的ITextRender池
     * @param size 池大小
     * */
    private void createITextRenderPool(int size){
        for (int i = 0;i < size;i++){
            if (this.iTextRendererPool != null && this.iTextRendererPool.size() >= this.DEFAULT_MAX_ITEXTRENDERER_SIZE){
                return;
            }

            if (this.iTextRendererPool == null){
                this.iTextRendererPool = new HashSet<>();
            }
            this.iTextRendererPool.add(new PooledITextRenderer());
        }
    }

    /**
     * 获取一个ITextRender实例
     * */
    public synchronized PooledITextRenderer getTextRenderer(){
        PooledITextRenderer pooledITextRenderer = null;
        if (this.iTextRendererPool == null || this.iTextRendererPool.isEmpty()){
            return pooledITextRenderer;
        }

        pooledITextRenderer = this.getFreePooledITextRenderer();

        while (pooledITextRenderer == null){
            try {
                this.wait(SLEEPINTERVALMILLS);
                pooledITextRenderer = this.getFreePooledITextRenderer();
            } catch (InterruptedException e) {
                LOGGER.error(LogConst.CLOGTITLE_SYSTEMMONITOR,e);
            }
        }
        return pooledITextRenderer;
    }

    /**
     * 获取一个空闲可用实例
     * */
    private PooledITextRenderer getFreePooledITextRenderer(){
        PooledITextRenderer freePooledITextRenderer = this.findFreePooledITextRenderer();
        if (freePooledITextRenderer == null){
            this.createITextRenderPool(this.DEFAULT_INCREMENT_ITEXTRENDERER_INSTANCE);
            freePooledITextRenderer = this.findFreePooledITextRenderer();
        }
        return freePooledITextRenderer;
    }

    private PooledITextRenderer findFreePooledITextRenderer(){
        PooledITextRenderer pooledITextRenderer = null;
        for (PooledITextRenderer inst : this.iTextRendererPool){
            if (!inst.isBusy){
                pooledITextRenderer = inst;
                inst.setBusy(true);
                break;
            }
        }
        return pooledITextRenderer;
    }

    /**
     * 释放ITextRender实例
     * @param pooledITextRenderer 需要释放的实例
     * */
    public synchronized void releaseITextRenderer(PooledITextRenderer pooledITextRenderer){
        if (this.iTextRendererPool == null || this.iTextRendererPool.isEmpty()){
            return;
        }

        if (this.iTextRendererPool.contains(pooledITextRenderer)){
            pooledITextRenderer.setBusy(false);
        }
    }

    /**
     * Inner Class -- ITextRenderer包装类
     * */
    class PooledITextRenderer extends ITextRenderer {

        private boolean isBusy;

        public PooledITextRenderer(){
            init();
        }

        public boolean isBusy() {
            return isBusy;
        }

        public void setBusy(boolean busy) {
            isBusy = busy;
        }

        private void init(){
            ITextFontResolver fontResolver = super.getFontResolver();

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
                LOGGER.error(LogConst.CLOGTITLE_SYSTEMMONITOR,e);
            }
        }
    }
}
