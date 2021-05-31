package top.oneyoung.i18n.starter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import top.oneyoung.i18n.ErrorMessage;
import top.oneyoung.i18n.I18n;

import javax.annotation.PostConstruct;

/**
 * I18nAutoConfiguration
 *
 * @author oneyoung
 * @since  2021/5/31 10:47
 */
@Configuration
@ConditionalOnClass({I18n.class})
@EnableConfigurationProperties(I18nConfigProperty.class)
public class I18nAutoConfiguration {

    private final I18nConfigProperty i18nConfigProperty;

    public I18nAutoConfiguration(I18nConfigProperty i18nConfigProperty) {
        this.i18nConfigProperty = i18nConfigProperty;
    }

    @ConditionalOnClass(ErrorMessage.class)
    @PostConstruct
    public void setLocale() {
        ErrorMessage.configure(i18nConfigProperty.getActive());
    }


}
