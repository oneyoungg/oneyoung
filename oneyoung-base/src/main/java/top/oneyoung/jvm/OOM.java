package top.oneyoung.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX SurvivorRatio=8
 *
 * @author oneyoung
 * @since 2023/7/14 12:59
 */
public class OOM {
    public static void main(String[] args) {
        HeapOOM();

    }

    public static void HeapOOM() {
        List<OOMObject> list = new ArrayList<OOMObject>();
        while (true) {
            list.add(new OOMObject());
        }
    }

    static class OOMObject {
        byte[] placeholder = new byte[1024 * 1024];
    }



}
