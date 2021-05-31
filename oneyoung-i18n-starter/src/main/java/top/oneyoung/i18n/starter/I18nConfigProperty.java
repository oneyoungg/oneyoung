package top.oneyoung.i18n.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;


/**
 * I18nAutoConfig
 *
 * @author oneyoung
 * @since 0.0.1
 */


@ConfigurationProperties(prefix = "oneyoung.i18n")

public class I18nConfigProperty {

    private Locale active = Locale.getDefault();

    public Locale getActive() {
        return active;
    }

    public void setActive(Locale active) {
        this.active = active;
    }
}
