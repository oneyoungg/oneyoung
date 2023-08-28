package top.oneyoung.springdemo.comnponet;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import top.oneyoung.springdemo.color.Green;
import top.oneyoung.springdemo.color.White;

/**
 * ColorSelect
 *
 * @author oneyoung
 * @since 2023/8/28 10:53
 */
public class ColorSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{Green.class.getName(), White.class.getName()};
    }


}
