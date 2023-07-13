package top.oneyoung.designpattern.observer;

import java.io.File;

/**
 * LogOpenListener
 *
 * @author oneyoung
 * @since 2023/7/13 15:35
 */

public class LogOpenListener implements EventListener {
    private File log;

    public LogOpenListener(String fileName) {
        this.log = new File(fileName);
    }

    @Override
    public void update(String eventType, File file) {
        System.out.println("Save to log " + log + ": Someone has performed " + eventType + " operation with the following file: " + file.getName());
    }
}
