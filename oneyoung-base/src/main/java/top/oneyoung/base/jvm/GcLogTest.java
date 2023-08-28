package top.oneyoung.base.jvm;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * GcLogTest
 *
 * @author oneyoung
 * @since 2023-08-27 15:08
 */

public class GcLogTest {

        public static void main(String[] args) throws InterruptedException {
            List<byte[]> list = new java.util.ArrayList<>();
            for (int i = 1000; i > 0; i--) {
                TimeUnit.MILLISECONDS.sleep(200);
                byte[] bytes = new byte[1024 * 1024];
                list.add(bytes);
            }
        }
}
