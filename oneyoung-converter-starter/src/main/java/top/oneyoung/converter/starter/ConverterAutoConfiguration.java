package top.oneyoung.converter.starter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.oneyoung.converter.Converter;
import top.oneyoung.converter.factory.ConverterFactory;

/**
 * ConverterConfigProperty
 *
 * @author oneyoung
 * @since 2022/4/15 21:45
 */
@Configuration
@ConditionalOnClass({Converter.class})
@EnableConfigurationProperties(ConverterConfigProperty.class)
public class ConverterAutoConfiguration {

    private final ConverterConfigProperty converterConfigProperty;

    public ConverterAutoConfiguration(ConverterConfigProperty property) {
        this.converterConfigProperty = property;
    }

    @Bean
    public SpringFactoryRegister converterFactorySpring() {
        ConverterFactory.setAutoConvert(converterConfigProperty.isAutoConvert());
        return new SpringFactoryRegister();
    }


}
