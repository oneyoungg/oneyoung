package top.oneyoung.designpattern.facade;

/**
 * BitrateReader
 *
 * @author oneyoung
 * @since 2023/7/12 10:48
 */
public class BitrateReader {
    public static VideoFile read(VideoFile file, Codec codec) {
        System.out.println("BitrateReader: reading file...");
        return file;
    }

    public static VideoFile convert(VideoFile buffer, Codec codec) {
        System.out.println("BitrateReader: writing file...");
        return buffer;
    }
}
