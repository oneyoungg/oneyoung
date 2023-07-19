package top.oneyoung.base;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class ZeroCopyExample {
    public static void main(String[] args) throws Exception {
        // 创建输入文件和输出文件通道
        try (FileInputStream inStream = new FileInputStream("LICENSE");
             FileOutputStream outStream = new FileOutputStream("LICENSE1");
             FileChannel inChannel = inStream.getChannel();
             FileChannel outChannel = outStream.getChannel()) {
            // 创建MappedByteBuffer，将输入文件通道的数据映射到内存中
            MappedByteBuffer inBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());

            // 直接将数据从输入缓冲区传输到输出缓冲区
            outChannel.write(inBuffer);
        }
    }
}
