package top.oneyoung.designpattern.decorator;

/**
 * DataSource
 *
 * @author oneyoung
 * @since 2022/3/23 15:09
 */
public interface DataSource {
    /**
     * 写
     *
     * @param data 字符串
     */
    void writeData(String data);

    /**
     * 读
     *
     * @return 字符串
     */
    String readData();
}
