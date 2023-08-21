package top.oneyoung.base;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * IODEmo
 *
 * @author oneyoung
 * @since 2023/7/21 021 16:41
 */

public class IODEmo {
    public static void main(String[] args) {
        printPath("D:\\project\\oneyoung\\oneyoung-base\\src\\main\\java");
    }

    public static void printPath(String path){
        File file = new File(path);
        if (!file.exists()){
            return;
        }
        if (file.isDirectory()){
            File[] files = file.listFiles();
            if (files != null && files.length > 0){
                for (File f : files) {
                    printPath(f.getPath());
                }
            }
        } else {
            System.out.println(file.getPath());
        }
    }

    public static void copyFile() {
        try (
            BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(Paths.get("123.txt")));
            BufferedOutputStream bos = new BufferedOutputStream(Files.newOutputStream(Paths.get("123copy.txt")));
        ) {
            int size;
            byte[] buf = new byte[1024];
            while ((size = bis.read(buf)) != -1) {
                bos.write(buf, 0, size);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
