package utils;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PDFUtils {
    @Test
    public void test1() {
        String filePath = "C:\\Users\\Administrator\\Desktop\\test\\技术分享-技术晋级之路.pdf";
        List<String> imageList = pdfToImagePath(filePath);
        Iterator<String> iterator = imageList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test2() {
        String filePath = "C:\\Users\\Administrator\\Desktop\\test\\技术分享-技术晋级之路.pdf";
        pdf2multiImage(filePath, "C:\\Users\\Administrator\\Desktop\\test\\test.jpg");
    }

    /**
     * 将PDF按页数每页转换成一个jpg图片
     *
     * @param filePath pdf文件路径；例如C:\Users\Administrator\Desktop\test\技术分享-技术晋级之路.pdf
     *                 会生成C:\Users\Administrator\Desktop\test\技术分享-技术晋级之路文件夹，下面就是
     *                 将pdf文件按照每页生成一张图片的方式生成的图片
     */
    public static List<String> pdfToImagePath(String filePath) {
        List<String> list = new ArrayList<>();
        //获取去除后缀的文件路径（去掉.pdf）
        String fileDirectory = filePath.substring(0, filePath.lastIndexOf("."));

        String imagePath;
        File file = new File(filePath);
        try {
            File f = new File(fileDirectory);
            if (!f.exists()) {
                f.mkdir();
            }
            PDDocument doc = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(doc);
            //获取pdf中的页数
            int pageCount = doc.getNumberOfPages();
            for (int i = 0; i < pageCount; i++) {
                // 方式1,第二个参数是设置缩放比(即像素)
                // BufferedImage image = renderer.renderImageWithDPI(i, 296);
                // 方式2,第二个参数是设置缩放比(即像素)
                //第二个参数越大生成图片分辨率越高，转换时间也就越长
                BufferedImage image = renderer.renderImage(i, 1.25f);
                imagePath = fileDirectory + "/" + i + ".jpg";
                ImageIO.write(image, "PNG", new File(imagePath));
                list.add(imagePath);
            }
            //关闭文件,不然该pdf文件会一直被占用。
            doc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 关于产生2种报错信息的说明
     * javax.imageio.IIOException: Maximum supported image dimension is 65500 piexls
     *
     * 产生这种错误的原因是我想将多页的pdf转成1张长图片（jpg格式会有，而png则不会）所致，经本人多次尝试，这个上限大概在50-60张之间，超过的话，基本100%会报这个错
     * 数组下标越界异常
     *
     * 产生这个错误的原因是因为上面的代码种，在写完1张图片后，未将shiftHeight置为0所致
     * ————————————————
     * 版权声明：本文为CSDN博主「淡出了少年」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/YatesJu/article/details/83687371
     */

    /**
     * @param pdfFile pdf文件路径；例如C:\Users\Administrator\Desktop\test\技术分享-技术晋级之路.pdf
     * @param outpath 生成的一张图片的路径，这个文件可以不存在，会自动创建，但是不能指定一个目录
     *                正确例如：C:\Users\Administrator\Desktop\test\test.jpg
     * @description pdf转成一张图片
     */
    private static void pdf2multiImage(String pdfFile, String outpath) {
        try {
            InputStream is = new FileInputStream(pdfFile);
            PDDocument pdf = PDDocument.load(is);
            int actSize = pdf.getNumberOfPages();
            List<BufferedImage> piclist = new ArrayList<BufferedImage>();
            for (int i = 0; i < actSize; i++) {
                BufferedImage image = new PDFRenderer(pdf).renderImageWithDPI(i, 130, ImageType.RGB);
                piclist.add(image);
            }
            yPic(piclist, outpath);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将宽度相同的图片，竖向追加在一起 ##注意：宽度必须相同
     *
     * @param piclist 文件流数组
     * @param outPath 输出路径
     */
    public static void yPic(List<BufferedImage> piclist, String outPath) {// 纵向处理图片
        if (piclist == null || piclist.size() <= 0) {
            System.out.println("图片数组为空!");
            return;
        }
        try {
            int height = 0, // 总高度
                    width = 0, // 总宽度
                    _height = 0, // 临时的高度 , 或保存偏移高度
                    __height = 0, // 临时的高度，主要保存每个高度
                    picNum = piclist.size();// 图片的数量
            int[] heightArray = new int[picNum]; // 保存每个文件的高度
            BufferedImage buffer = null; // 保存图片流
            List<int[]> imgRGB = new ArrayList<int[]>(); // 保存所有的图片的RGB
            int[] _imgRGB; // 保存一张图片中的RGB数据
            for (int i = 0; i < picNum; i++) {
                buffer = piclist.get(i);
                heightArray[i] = _height = buffer.getHeight();// 图片高度
                if (i == 0) {
                    width = buffer.getWidth();// 图片宽度
                }
                height += _height; // 获取总高度
                _imgRGB = new int[width * _height];// 从图片中读取RGB
                _imgRGB = buffer.getRGB(0, 0, width, _height, _imgRGB, 0, width);
                imgRGB.add(_imgRGB);
            }
            _height = 0; // 设置偏移高度为0
            // 生成新图片
            BufferedImage imageResult = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < picNum; i++) {
                __height = heightArray[i];
                if (i != 0) _height += __height; // 计算偏移高度
                imageResult.setRGB(0, _height, width, __height, imgRGB.get(i), 0, width); // 写入流中
            }
            File outFile = new File(outPath);
            ImageIO.write(imageResult, "jpg", outFile);// 写图片
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}