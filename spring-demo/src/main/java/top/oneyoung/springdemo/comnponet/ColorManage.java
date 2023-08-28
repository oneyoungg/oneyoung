package top.oneyoung.springdemo.comnponet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import top.oneyoung.springdemo.color.Black;
import top.oneyoung.springdemo.color.Blue;
import top.oneyoung.springdemo.color.Red;

/**
 * ColorManage
 *
 * @author oneyoung
 * @since 2023/8/28 10:19
 */
@Configuration
//@Import(Black.class)
@Import({ColorSelector.class, ColorImportBeanDefinitionRegistrar.class, Black.class})
public class ColorManage {

    @Bean
    public Red red() {
        return new Red();
    }

    @Bean
//    @Conditional(ColorCondition.class)
    public Blue blue() {
        return new Blue();
    }



}
