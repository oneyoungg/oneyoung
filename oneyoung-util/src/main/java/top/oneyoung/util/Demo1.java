//package top.oneyoung.util;
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.net.URL;
//
///**
// * Demo1
// *
// * @author oneyoung
// * @since 2023/7/15 12:39
// */
//public class Demo1 {
//    public static void main(String[] args) {
//        String url = "https://baidu.com"; // 输入需要截图的网址
//
//        try {
//            // 创建HeadlessGraphicsEnvironment
//            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//            GraphicsDevice gd = ge.getDefaultScreenDevice();
//            GraphicsConfiguration gc = gd.getDefaultConfiguration();
//            BufferedImage image = gc.createCompatibleImage(1920, 1080); // 设置截图的宽度和高度
//
//            // 创建Graphics2D对象
//            Graphics2D g2d = image.createGraphics();
//            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//            // 加载网页并绘制到图像
//            URL webpageUrl = new URL(url);
//            g2d.setClip(0, 0, 1920, 1080); // 设置截图的区域
//            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
//            g2d.fillRect(0, 0, 1920, 1080); // 设置背景颜色
//            g2d.setColor(Color.BLACK);
//            g2d.drawString("Loading...", 10, 20); // 绘制加载文本
//
//            // 等待网页加载完成
//            Thread.sleep(5000);
//
//            // 绘制网页内容到图像
//            g2d.drawImage(ImageIO.read(webpageUrl), 0, 0, null);
//            g2d.dispose();
//
//            // 保存图像到文件
//            ImageIO.write(image, "png", new File("screenshot.png")); // 设置保存截图的文件名和格式
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//}
