package top.oneyoung.converter.stream;

/**
 * @author oneyoung
 * @since 2020/1/18 11:01
 */
public interface BaseConverterStream<Input, S extends BaseConverterStream<Input, S>> {
    /**
     * 合并
     *
     * @param merge   要合并的类
     * @param <Merge> 要合并的类型
     * @return 新的流
     */
    <Merge> S merge(Merge merge);

    /**
     * 合并
     *
     * @param merge      要合并的类
     * @param mergeClass 普通类存在继承时，可指定父类
     * @param <Merge>    要合并的类型
     * @return 新的流
     */
    <Merge> S merge(Merge merge, Class<Merge> mergeClass);

    /**
     * 填充
     *
     * @param fillClass 需要填充的类型
     * @param objects   额外传入的参数，作为扩展
     * @param <Fill>    需要填充的类型
     * @return 新的流
     */
    <Fill> S fill(Class<Fill> fillClass, Object... objects);

    /**
     * 获取当前流的内容
     *
     * @return Input
     */
    Input get();
}
