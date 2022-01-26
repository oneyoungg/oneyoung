package top.oneyoung.converter.stream;

import top.oneyoung.converter.stream.collection.ConverterCollectionStream;
import top.oneyoung.converter.stream.collection.ConverterListStream;

import java.util.Collection;
import java.util.List;

/**
 * 1. 这个接口的实现是个实例，一个有状态的对象，会保存传入的对象。每次变换都会产生一个新的实例。
 * 2. 类似于RxJava的变换，和JAVA8的Stream实现有一些区别。
 * 3. 这个转换器可以处理普通类转换、集合类转换，但集合类转换需要强制转换，无法编译时校验，
 * 因此建议使用{@link ConverterStream#fromCollection(Collection, Class)}
 * 这是专门为集合类转换适配的转换器
 *
 * @author oneyoung
 * @since 2020/1/15 22:32
 */
public interface ConverterStream<I> extends BaseConverterStream<I, ConverterStream<I>> {


    /**
     * 普通类使用
     *
     * @param input 输入
     * @param <I>   输入的类型
     * @return result
     */
    static <I> ConverterStream<I> from(I input) {
        return new PipelineConverter<>(input);
    }


    /**
     * 普通类存在继承时，可指定父类
     *
     * @param input      输入
     * @param inputClass 输入class
     * @param <I>        输入
     * @return result
     */
    static <I> ConverterStream<I> from(I input, Class<I> inputClass) {
        return new PipelineConverter<>(input);
    }

    /**
     * Collection集合类使用
     */
    static <I, C extends Collection<I>> ConverterCollectionStream<C> fromCollection(C input, Class<I> inputClass) {
        return ConverterCollectionStream.from(input, inputClass);
    }

    /**
     * List集合类使用
     */
    static <I, C extends List<I>> ConverterListStream<C> fromList(C input, Class<I> inputClass) {
        return ConverterListStream.from(input, inputClass);
    }

    /**
     * convertTo outputClass
     *
     * @return 新的流，Output
     */
    <O> ConverterStream<O> convert(Class<O> outputClass);
}
