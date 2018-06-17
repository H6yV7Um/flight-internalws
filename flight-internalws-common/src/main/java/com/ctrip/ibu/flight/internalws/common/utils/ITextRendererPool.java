package com.ctrip.ibu.flight.internalws.common.utils;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.SoftReferenceObjectPool;
import org.xhtmlrenderer.pdf.ITextRenderer;

/**
 * @author : kyxie
 * createtime : 2018/5/22 11:19
 */
public class ITextRendererPool extends SoftReferenceObjectPool<ITextRenderer> {

    public ITextRendererPool(PooledObjectFactory<ITextRenderer> factory) {
        super(factory);
    }
}
