package top.oneyoung.io;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * IOTest
 *
 * @author oneyoung
 * @since 2023/8/17 15:55
 */
public class IOTest {
    public static void main(String[] args) {
        String type = args[0];
        String inputFilePath = args[1];
        String outputFilePath = args[2];
        long start = System.currentTimeMillis();
        switch (type) {
            case "io":
                ioCopyFile(inputFilePath, outputFilePath);
                break;
            case "buffer":
                bufferCopyFile(inputFilePath, outputFilePath);
                break;
            case "mmap":
                mmapCopyFile(inputFilePath, outputFilePath);
                break;
            case "sendfile":
                sendfileCopyFile(inputFilePath, outputFilePath);
                break;
            default:
                System.out.println("type error");
        }
        long end = System.currentTimeMillis();
        System.out.println("time: " + (end - start));
    }

    private static void sendfileCopyFile(String inputFilePath, String outputFilePath) {

        try (FileInputStream fileInputStream = new FileInputStream(inputFilePath);
             FileOutputStream fileOutputStream = new FileOutputStream(outputFilePath);
             FileChannel fileChannelIn = fileInputStream.getChannel();
             FileChannel fileChannelOut = fileOutputStream.getChannel()) {
            fileChannelIn.transferTo(0, fileChannelIn.size(), fileChannelOut);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void mmapCopyFile(String inputFilePath, String outputFilePath) {
        // zero copy

        try (FileInputStream fileInputStream = new FileInputStream(inputFilePath);
             RandomAccessFile fileOutputStream = new RandomAccessFile(outputFilePath,"rw");
             FileChannel fileChannelIn = fileInputStream.getChannel();
             FileChannel fileChannelOut = fileOutputStream.getChannel()) {
            MappedByteBuffer mappedInByteBuffer = fileChannelIn.map(FileChannel.MapMode.READ_ONLY, 0, fileChannelIn.size());
            MappedByteBuffer mappedOutByteBuffer = fileChannelOut.map(FileChannel.MapMode.READ_WRITE, 0, fileChannelIn.size());
            byte[] buffer = new byte[1024];
            while (mappedInByteBuffer.hasRemaining()) {
                int len = Math.min(mappedInByteBuffer.remaining(), buffer.length);
                mappedInByteBuffer.get(buffer, 0, len);
                mappedOutByteBuffer.put(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static void bufferCopyFile(String inputFilePath, String outputFilePath) {
        try (BufferedInputStream fis = new BufferedInputStream(new FileInputStream(inputFilePath), 64);
             BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(outputFilePath), 64)) {
            int len = 0;
            while ((len = fis.read()) != -1) {
                fos.write(len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void ioCopyFile(String inputFilePath, String outputFilePath) {
        try (FileInputStream fis = new FileInputStream(inputFilePath);
             FileOutputStream fos = new FileOutputStream(outputFilePath)) {
            int len = 0;
            while ((len = fis.read()) != -1) {
                fos.write(len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
