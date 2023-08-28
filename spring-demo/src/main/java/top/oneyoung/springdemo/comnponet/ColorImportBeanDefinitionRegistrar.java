package top.oneyoung.springdemo.comnponet;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import top.oneyoung.springdemo.color.Pink;

/**
 * color 自定义注册器
 *
 * @author oneyoung
 * @since 2023/8/28 11:05
 */
public class ColorImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        if (registry.containsBeanDefinition("top.oneyoung.springdemo.color.Black")) {
            registry.registerBeanDefinition("Pink", new RootBeanDefinition(Pink.class));
        }
    }
}
