//package top.oneyoung.util;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.net.URL;
//
///**
// * Demo
// *
// * @author oneyoung
// * @since 2023/7/15 12:31
// */
//public class Demo {
//
//    public static void main(String[] args) {
//        // 设置网页URL
//        String url = "https://www.qq.com";
//
//        try {
//            // 使用jsoup库获取网页内容
//            Document document = Jsoup.connect(url).get();
//
//            // 模拟正常浏览器的窗口大小
//            int width =   1000;
//            int height =  1000;
//
//            // 创建BufferedImage对象，设置宽度和高度
//            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//
//            // 创建Graphics2D对象，用于绘制网页内容
//            Graphics2D graphics = image.createGraphics();
//
//            // 设置背景色为白色
//            graphics.setColor(Color.WHITE);
//            graphics.fillRect(0, 0, width, height);
//
//            // 遍历网页中的每个元素，并绘制到BufferedImage上
//            Elements elements = document.select("body").first().children();
//            for (Element element : elements) {
//                // 获取元素的位置和大小
//                int x = Integer.parseInt(element.attr("offsetLeft"));
//                int y = Integer.parseInt(element.attr("offsetTop"));
//                int elementWidth = Integer.parseInt(element.attr("offsetWidth"));
//                int elementHeight = Integer.parseInt(element.attr("offsetHeight"));
//
//                // 绘制元素到BufferedImage上
//                graphics.drawImage(getImage(element.absUrl("src")), x, y, elementWidth, elementHeight, null);
//            }
//
//            // 保存截图到本地
//            ImageIO.write(image, "png", new File("screenshot.png"));
//
//            System.out.println("截图成功，文件保存在: screenshot.png");
//        } catch (IOException e) {
//            System.out.println("截图失败: " + e.getMessage());
//        }
//    }
//
//    // 根据URL获取图片
//    private static Image getImage(String url) throws IOException {
//        return ImageIO.read(new URL(url));
//    }
//}
