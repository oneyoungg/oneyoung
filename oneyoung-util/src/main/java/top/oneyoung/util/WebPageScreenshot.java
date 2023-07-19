package top.oneyoung.util;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import ru.yandex.qatools.ashot.shooting.ShootingStrategy;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * WebPageScreenshot
 *
 * @author oneyoung
 * @since 2023/7/15 12:13
 */
public class WebPageScreenshot {
    public static void main(String[] args) {
        String url = "https://qq.com"; // 输入需要截图的网址

        // 设置ChromeDriver的路径
        System.setProperty("webdriver.chrome.driver", "/Users/oneyoung/Downloads/chromedriver/chromedriver");

        // 配置ChromeOptions，启用无界面模式
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // 启用无界面模式
        options.addArguments("--remote-allow-origins=*");


        // 创建ChromeDriver对象
        WebDriver driver = new ChromeDriver(options);
        WebDriver.Options manage = driver.manage();
        WebDriver.Window window = manage.window();
        window.setSize(new Dimension(1920, 1080));
//        window.fullscreen();
//        // 我需要截取整个网页，而不只是上面的可见部分
//        window.maximize();
        try {
            // 打开网页
            driver.get(url);

            // 等待网页加载完成
            Thread.sleep(2000);
            // 创建AShot对象，并设置截图策略为整个网页
            long dpr = (long)((JavascriptExecutor) driver).executeScript("return window.devicePixelRatio;");
            ShootingStrategy shootingStrategy = ShootingStrategies.viewportRetina(1000, 0, 0, dpr);

            AShot ashot = new AShot().shootingStrategy(shootingStrategy);


            // 截图并保存
            Screenshot screenshot = ashot.takeScreenshot(driver);
            BufferedImage image = screenshot.getImage();

            ImageIO.write(image, "PNG", new File("screenshot.png"));

//
//            // 截图
//            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//
//            // 保存截图到文件
//            // 截图并保存
//            FileUtils.copyFile(screenshot, new File("screenshot.png")); // 设置保存截图的文件名和格式
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 关闭浏览器
            driver.quit();
        }
    }
}
