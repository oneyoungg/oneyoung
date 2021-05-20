package top.oneyoung.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application
 *
 * @author oneyoung
 * @since 2021/4/18 018 22:12
 */

@SpringBootApplication(scanBasePackages = {"top.oneyoung"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
