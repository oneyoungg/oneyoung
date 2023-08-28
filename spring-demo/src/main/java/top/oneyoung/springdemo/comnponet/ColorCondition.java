package top.oneyoung.springdemo.comnponet;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.CollectionUtils;
import top.oneyoung.springdemo.color.Color;

import java.util.Map;

/**
 * ColorCondition
 *
 * @author oneyoung
 * @since 2023/8/28 10:28
 */
public class ColorCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        BeanDefinitionRegistry registry = context.getRegistry();
        Map<String, Color> beansOfType = context.getBeanFactory().getBeansOfType(Color.class);
        if (CollectionUtils.isEmpty(beansOfType)) {
            return true;
        }
        return false;
    }

    public ColorCondition() {
        // TODO document why this constructor is empty
    }
}
