package top.oneyoung.converter.stream.collection;


import top.oneyoung.converter.stream.BaseConverterStream;

import java.util.Collection;

/**
 * 这个接口和{@link com.alibaba.cz.base.tool.converter.stream.ConverterStream}的定义基本一样，但处理的是集合输入类型。
 *
 * @author oneyoung
 * @since 2020/1/15 22:32
 */
public interface ConverterCollectionStream<Input> extends BaseConverterStream<Input, ConverterCollectionStream<Input>> {

    /**
     * 通过 {@link com.alibaba.cz.base.tool.converter.stream.ConverterStream#fromCollection(Collection, Class)}使用
     */
    static <Input, Collect extends Collection<Input>> ConverterCollectionStream<Collect> from(Collect input, Class<Input> inputClass) {
        return new PipelineConverterCollection<>(input, inputClass);
    }

    /**
     * convertTo outputClass ，Collection
     *
     * @return 新的流，Output
     */
    <Output> ConverterCollectionStream<Collection<Output>> convert(Class<Output> outputClass);
}
