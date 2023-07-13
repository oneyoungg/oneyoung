package top.oneyoung.designpattern.observer;

import java.io.File;

/**
 * EmailNotificationListener
 *
 * @author oneyoung
 * @since 2023/7/13 15:34
 */
public class EmailNotificationListener implements EventListener {
    private String email;

    public EmailNotificationListener(String email) {
        this.email = email;
    }

    @Override
    public void update(String eventType, File file) {
        System.out.println("Email to " + email + ": Someone has performed " + eventType + " operation with the following file: " + file.getName());
    }
}
