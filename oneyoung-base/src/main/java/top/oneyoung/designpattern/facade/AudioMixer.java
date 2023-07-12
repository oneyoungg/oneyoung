package top.oneyoung.designpattern.facade;

import java.io.File;

/**
 * AudioMixer
 *
 * @author oneyoung
 * @since 2023/7/12 10:49
 */
public class AudioMixer {
    public File fix(VideoFile result){
        System.out.println("AudioMixer: fixing audio...");
        return new File("tmp");
    }
}
