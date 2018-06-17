package com.ctrip.ibu.flight.internalws.common.utils;

import com.ctrip.framework.clogging.agent.log.ILog;
import com.ctrip.framework.clogging.agent.log.LogManager;
import com.ctrip.ibu.flight.internalws.common.log.LoggerHelper;
import com.ctrip.ibu.flight.internalws.models.common.PdfGenerateResult;
import com.ctrip.ibu.flight.internalws.models.exception.common.PdfException;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import org.apache.commons.lang.StringUtils;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Pdf工具类
 * @author : kyxie
 * createtime : 2018/5/28 14:14
 */
public class PdfUtilV2 {

    private static final ILog LOGGER = LogManager.getLogger(PdfUtilV2.class);

    private static ITextRendererPoolManager iTextRendererPoolManager = ITextRendererPoolManager.getInstance();

    /**
     * 将HTML转换为PDF字节流
     * @param htmlStrList 需要转换的HTML列表
     * @return PDF字节流
     * */
    public static byte[] convertHtmlToPdfByteArray(List<String> htmlStrList) throws PdfException {
        if (htmlStrList == null || htmlStrList.isEmpty()){
            return new byte[0];
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4);

        int pageIndex = 0;
        int htmlCnt = 0;
        try {
            PdfCopy pdfCopy = new PdfCopy(document,outputStream);
            document.open();

            for (;htmlCnt < htmlStrList.size();htmlCnt++){
                String html = htmlStrList.get(htmlCnt);
                PdfReader pdfReader = new PdfReader(getPdfStreamFromHtmlStr(html));

                for (int i = 0;i < pdfReader.getNumberOfPages();){
                    PdfImportedPage page = pdfCopy.getImportedPage(pdfReader,++i);
                    PdfCopy.PageStamp pageStamp = pdfCopy.createPageStamp(page);
                    ColumnText.showTextAligned(pageStamp.getUnderContent(), Element.ALIGN_CENTER,new Phrase(String.valueOf(i + pageIndex)),50f,15f,0);
                    pageStamp.alterContents();;
                    pdfCopy.addPage(page);
                }

                pageIndex += pdfReader.getNumberOfPages();
            }

            document.close();

        } catch (DocumentException e) {
            throw new PdfException(PdfGenerateResult.DOCUMENTERROR,String.format("生成第%d封PDF Document异常，异常信息：%s",htmlCnt,ThrowableUtils.getExceptionDesc(e)));
        } catch (IOException e) {
            throw new PdfException(PdfGenerateResult.IOERROR,String.format("生成第%d封PDF IO异常，异常信息：%s",htmlCnt,ThrowableUtils.getExceptionDesc(e)));
        } catch (Exception e){
            throw new PdfException(PdfGenerateResult.NORMALERROR,String.format("生成第%d封PDF异常，异常信息：%s",htmlCnt,ThrowableUtils.getExceptionDesc(e)));
        } finally {
            byte[] result = outputStream.toByteArray();

            try {
                outputStream.close();
            } catch (IOException e) {
                LOGGER.error(e);
            }

            if (result != null && result.length >= 1) return result;
        }
        return null;
    }

    /**
     * 获取单个HTML的字节流
     * @param htmlStr HTML字符串
     * */
    private static byte[] getPdfStreamFromHtmlStr(String htmlStr){
        if (StringUtils.isEmpty(htmlStr)){
            return new byte[0];
        }

        byte[] result = null;

        ITextRenderer iTextRenderer = null;

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            iTextRenderer = iTextRendererPoolManager.borrowObject();

            LoggerHelper.appendResponseContent(String.format("当前ITextRendererPool中ActivedNum = %d, IdleNum = %d",iTextRendererPoolManager.getNumActive(),iTextRendererPoolManager.getNumIdle()));

            iTextRenderer.setDocumentFromString(htmlStr, null);

            iTextRenderer.layout();

            iTextRenderer.createPDF(outputStream);

            result = outputStream.toByteArray();

        } catch (Exception e) {
            LOGGER.error(e);
            LoggerHelper.appendResponseContent(String.format("Html字符串生成PDF字节流时出现异常，异常信息：\n%s",ThrowableUtils.getExceptionDesc(e)));
        } finally {
            if (iTextRenderer != null){
                try {
                    iTextRendererPoolManager.returnObject(iTextRenderer);
                } catch (Exception e) {
                    LOGGER.error(e);
                    LoggerHelper.appendResponseContent(String.format("返还ITextRenderer池实例时出现异常，异常信息：\n%s",ThrowableUtils.getExceptionDesc(e)));
                }
            }
        }
        return result;
    }

}
