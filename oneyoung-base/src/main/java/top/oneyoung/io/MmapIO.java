//package top.oneyoung.io;
//
//import java.io.RandomAccessFile;
//import java.nio.MappedByteBuffer;
//import java.nio.channels.FileChannel;
//
///**
// * MmapIO
// *
// * @author oneyoung
// * @since 2023/8/17 15:29
// */
//public class MmapIO {
//    public static void main(String[] args) {
//
//    }
//
//    public static void copyFile(String src, String dest) throws Exception {
//        try (RandomAccessFile randomAccessFile = new RandomAccessFile("LICENSE", "rw");) {
//            FileChannel fileChannel = randomAccessFile.getChannel();
//            MappedByteBuffer map = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
//            map.c
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//}
