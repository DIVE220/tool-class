package com.fang.generalTools.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;

/**
 * @author Mr.Fang
 * @description PDF动态生成
 * @date 2021/12/29
 */
public class PDFUtil {

    public static void test1(){//生成pdf
        BaseFont bf;
        Font font = null;
        try {
            bf = BaseFont.createFont( "STSong-Light", "UniGB-UCS2-H",
                    BaseFont.NOT_EMBEDDED);//创建字体
            font = new Font(bf,12);//使用字体
        } catch (Exception e) {
            e.printStackTrace();
        }
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("E:/测试.pdf"));
            document.open();
            document.add(new Paragraph("就是测试下",font));//引用字体
            document.add(new Paragraph("真的测试下",font));//引用字体

            float[] widths = {25f,25f,25f };// 设置表格的列宽和列数 默认是4列
            PdfPTable table = new PdfPTable(widths);// 建立一个pdf表格
            table.setSpacingBefore(20f);
            table.setWidthPercentage(100);// 设置表格宽度为100%

            PdfPCell cell = null;
            cell = new PdfPCell(new Paragraph("姓名",font));//
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("性别",font));//
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("身份证号",font));//
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            //以下代码的作用是创建100行数据,其中每行有四列,列依次为 编号 姓名 性别 备注
            for (int i = 1; i <=10; i++) {
                //设置编号单元格
                PdfPCell cell11=new PdfPCell(new Paragraph("aa名媛",font));
                PdfPCell cell22=new PdfPCell(new Paragraph("bb女",font));
                PdfPCell cell33=new PdfPCell(new Paragraph("cc花姑娘",font));

                //单元格水平对齐方式
                cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
                //单元格垂直对齐方式
                cell11.setVerticalAlignment(Element.ALIGN_CENTER);

                cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell22.setVerticalAlignment(Element.ALIGN_CENTER);

                cell33.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell33.setVerticalAlignment(Element.ALIGN_CENTER);


                table.addCell(cell11);
                table.addCell(cell22);
                table.addCell(cell33);

            }
            document.add(table);

            document.close();
        } catch (Exception e) {
            System.out.println("file create exception");
        }

    }
    public static void main(String[] args) throws InterruptedException {
        test1();

    }
}
