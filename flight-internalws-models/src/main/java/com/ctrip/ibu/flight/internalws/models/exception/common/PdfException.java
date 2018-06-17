package com.ctrip.ibu.flight.internalws.models.exception.common;

import com.ctrip.ibu.flight.internalws.models.common.PdfGenerateResult;

/**
 * PDF生成异常
 * Create by kyxie on 2018/2/9 11:56
 */
public class PdfException extends Exception {

    private String errorMsg;

    private PdfGenerateResult pdfGenerateResult;

    public PdfException(PdfGenerateResult pdfGenerateResult,String errorMsg){
        super(errorMsg);
        this.pdfGenerateResult = pdfGenerateResult;
        this.errorMsg = errorMsg;
    }

    public PdfException(String errorMsg){
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public PdfGenerateResult getPdfGenerateResult() {
        return pdfGenerateResult;
    }
}
