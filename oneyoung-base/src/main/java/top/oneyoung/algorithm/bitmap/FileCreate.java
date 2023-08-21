package top.oneyoung.algorithm.bitmap;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * FileCreate
 *
 * @author oneyoung
 * @since 2023-08-20 18:39
 */

public class FileCreate {
    public static void main(String[] args) throws Exception{
        File file = new File("dat.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(file, true);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
             BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        ){
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < 5000000; i++) {
                String name = RandomStringUtils.randomAlphabetic(5);
                String fileName = "https://www." + name + ".com\\" + i + "\n";
                builder.append(fileName);
            }
            bufferedWriter.write(builder.toString());
        } catch (Exception e){

        }
    }
}
