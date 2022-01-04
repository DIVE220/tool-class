package com.fang.generalTools.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

/**
 * @author Mr.Fang
 * @description 二维码工具类
 * @date 2021/12/27
 */
public class QRCodeUtil {

    private static final String CHARSET = "utf-8";

    private static final String FORMAT = "JPG";
    /**
     *二维码尺寸
     */
    private static final int QRCODE_SIZE = 150;
    /**
     *LOGO宽度
     */
    private static final int LOGO_WIDTH = 60;
    /**
     *LOGO高度
     */
    private static final int LOGO_HEIGHT = 60;


    /**
     *制作二维码
     * @author 方磊
     * @date 2021/12/29
      * @param content //内容
     * @param logoPath //LOGO地址
     * @param needCompress //是否需要压缩
     * @return java.awt.image.BufferedImage
     */
    private static BufferedImage createImage(String content, String logoPath, boolean needCompress) throws Exception {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        //二维码自动纠错，容错等级，H最高
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        //二维码字符集
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        //二维码边距
        hints.put(EncodeHintType.MARGIN, 0);
        //生成二维码工具类
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);

        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        if (logoPath == null || "".equals(logoPath)) {
            return image;
        }
        //插入图片
        QRCodeUtil.insertImage(image, logoPath, needCompress);
        return image;
    }


    /**
     *插入LOGO
     * @author 方磊
     * @date 2021/12/29
      * @param source //制作的二维码
     * @param logoPath //LOGO地址
     * @param needCompress //是否需要压缩
     */
    private static void insertImage(BufferedImage source, String logoPath, boolean needCompress) throws Exception {
        File file = new File(logoPath);
        if (!file.exists()) {
            throw new Exception("logo file not found.");
        }
        Image src = ImageIO.read(new File(logoPath));
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        //是否压缩LOGO
        if (needCompress) {
            if (width > LOGO_WIDTH) {
                width = LOGO_WIDTH;
            }
            if (height > LOGO_HEIGHT) {
                height = LOGO_HEIGHT;
            }
            Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null);
            g.dispose();
            src = image;
        }
        //插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (QRCODE_SIZE - width) / 2;
        int y = (QRCODE_SIZE - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }




    /**
     *判断生成地址是否有文件
     * @author 方磊
     * @date 2022/1/4
      * @param destPath //下载地址
     */
    public static void mkdirs(String destPath) {
        File file = new File(destPath);
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
    }


    /**
     *生成二维码
     * @author 方磊
     * @date 2022/1/4
      * @param content //参数
     * @param logoPath //LOGO地址
     * @param output //输出流
     * @param needCompress //是否压缩LOGO
     */
    public static void encode(String content, String logoPath, OutputStream output, boolean needCompress) throws Exception{
        BufferedImage image = createImage(content ,logoPath , needCompress);
        ImageIO.write(image , FORMAT , output);
    }


    /**
     *生成二维码
     * @author 方磊
     * @date 2022/1/4
      * @param content //参数
     * @param output //输出流
     */
    public static void encode(String content , OutputStream output) throws Exception{
        BufferedImage image = createImage(content ,null , false);
        ImageIO.write(image , FORMAT , output);
    }

    /**
     *生成二维码返回二维码文件名称
     * @author 方磊
     * @date 2021/12/29
     * @param content //内容
     * @param logoPath //LOGO地址
     * @param destPath //保存地址
     * @param fileName //文件名称
     * @param needCompress //是否需要压缩
     * @return java.lang.String
     */
    public static String encode(String content, String logoPath, String destPath, String fileName, boolean needCompress) throws Exception {
        BufferedImage image = createImage(content, logoPath, needCompress);
        mkdirs(destPath);
        fileName = fileName.substring(0, fileName.indexOf(".") > 0 ? fileName.indexOf(".") : fileName.length()) + "." + FORMAT.toLowerCase();
        ImageIO.write(image , FORMAT , new File(destPath + "/" + fileName));
        return fileName;
    }


    /**
     *功能描述
     * @author 方磊
     * @date 2022/1/4
      * @param content //参数
     * @param destPath //保存地址
     * @param fileName //文件名
     * @return java.lang.String //文件名
     */
    public static String encode(String content , String destPath, String fileName) throws Exception {
        BufferedImage image = createImage(content, null, false);
        mkdirs(destPath);
        fileName = fileName.substring(0, fileName.indexOf(".") > 0 ? fileName.indexOf(".") : fileName.length()) + "." + FORMAT.toLowerCase();
        ImageIO.write(image , FORMAT , new File(destPath + "/" + fileName));
        return fileName;
    }


    /**
     *功能描述
     * @author 方磊
     * @date 2022/1/4
      * @param content //参数
     * @param destPath //保存地址
     * @return java.lang.String //文件名
     */
    public static String encode(String content , String destPath) throws Exception {
        BufferedImage image = createImage(content, null, false);
        mkdirs(destPath);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String fileName = format.format(new Date());
        fileName = fileName + "." + FORMAT.toLowerCase();
        ImageIO.write(image , FORMAT , new File(destPath + "/" + fileName));
        return fileName;
    }

    ;

}
