package com.ctrip.ibu.flight.internalws.common.utils;

import com.ctrip.ibu.flight.internalws.models.common.PdfGenerateResult;
import com.ctrip.ibu.flight.internalws.models.exception.common.PdfException;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * PDF工具类
 * 建议使用PdfUtilV2
 * @see PdfUtilV2
 * Create by kyxie on 2018/3/2 11:43
 */
@Deprecated
@Component
public class PdfUtil {

    private PooledITextRendererManager pooledITextRendererManager;

    @Inject
    public PdfUtil(PooledITextRendererManager pooledITextRendererManager){
        this.pooledITextRendererManager = pooledITextRendererManager;
    }

    /**
     * 将HTML转换为PDF字节流
     * @param htmlStrList 需要转换的HTML列表
     * @return PDF字节流
     * */
    public byte[] convertHtmlToPdfByteArray(List<String> htmlStrList) throws PdfException {
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
            throw new PdfException(PdfGenerateResult.DOCUMENTERROR,String.format("生成第%d封PDF Document异常，异常信息：%s\n",htmlCnt,e.toString()));
        } catch (IOException e) {
            throw new PdfException(PdfGenerateResult.IOERROR,String.format("生成第%d封PDF IO异常，异常信息：%s\n",htmlCnt,e.toString()));
        } catch (Exception e){
            throw new PdfException(PdfGenerateResult.NORMALERROR,String.format("生成第%d封PDF异常，异常信息：%s\n",htmlCnt,e.toString()));
        } finally {
                byte[] result = outputStream.toByteArray();
                if (result != null && result.length >= 1) return result;

        }
        return null;
    }

    /**
     * 获取单个HTML的字节流
     * @param htmlStr HTML字符串
     * */
    private byte[] getPdfStreamFromHtmlStr(String htmlStr){
        if (StringUtils.isEmpty(htmlStr)){
            return new byte[0];
        }

        byte[] result = null;

        PooledITextRendererManager.PooledITextRenderer textRenderer = null;

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            textRenderer = pooledITextRendererManager.getTextRenderer();

            textRenderer.setDocumentFromString(htmlStr, null);

            textRenderer.layout();

            textRenderer.createPDF(outputStream);

            result = outputStream.toByteArray();

        } catch (Exception e) {
            //ignore
        } finally {
            pooledITextRendererManager.releaseITextRenderer(textRenderer);
        }
        return result;
    }
}
