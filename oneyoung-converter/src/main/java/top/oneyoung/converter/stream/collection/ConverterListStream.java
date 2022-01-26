package top.oneyoung.converter.stream.collection;


import top.oneyoung.converter.stream.BaseConverterStream;

import java.util.List;

/**
 * 这个接口和{@link com.alibaba.cz.base.tool.converter.stream.ConverterStream}的定义基本一样，但处理的是集合输入类型。
 *
 * @author oneyoung
 * @since 2020/1/15 22:32
 */
public interface ConverterListStream<Input> extends BaseConverterStream<Input, ConverterListStream<Input>> {

    /**
     * 通过 {@link com.alibaba.cz.base.tool.converter.stream.ConverterStream#fromList(List, Class)}使用
     */
    static <Input, Collect extends List<Input>> ConverterListStream<Collect> from(Collect input, Class<Input> inputClass) {
        return new PipelineConverterList<>(input, inputClass);
    }

    /**
     * convertTo outputClass ，List
     *
     * @return 新的流，Output
     */
    <Output> ConverterListStream<List<Output>> convert(Class<Output> outputClass);
}
