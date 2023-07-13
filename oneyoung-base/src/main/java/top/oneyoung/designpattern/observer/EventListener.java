package top.oneyoung.designpattern.observer;

import java.io.File;

/**
 * EventListener
 *
 * @author oneyoung
 * @since 2023/7/13 15:34
 */
public interface EventListener {
    void update(String eventType, File file);
}
