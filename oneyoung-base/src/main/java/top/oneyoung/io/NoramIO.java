package top.oneyoung.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * NoramIO
 *
 * @author oneyoung
 * @since 2023/8/17 15:24
 */
public class NoramIO {
    public static void main(String[] args) {
        String src = "LICENSE";
        String dest = "LICENSE1";
        copyFile(src, dest);
    }

    public static void copyFile(String src, String dest) {
        try (FileInputStream fis = new FileInputStream(src);
             FileOutputStream fos = new FileOutputStream(dest)){
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer)) != -1){
                fos.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
