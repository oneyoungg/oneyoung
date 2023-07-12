package top.oneyoung.designpattern.facade;

import java.io.File;

/**
 * Demo
 *
 * @author oneyoung
 * @since 2023/7/12 10:51
 */
public class Demo {
    public static void main(String[] args) {
        VideoConversionFacade converter = new VideoConversionFacade();
        File mp4Video = converter.convertVideo("youtubevideo.ogg", "mp4");
    }
}
