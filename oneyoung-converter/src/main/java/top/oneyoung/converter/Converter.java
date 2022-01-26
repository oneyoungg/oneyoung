package top.oneyoung.converter;

import top.oneyoung.converter.stream.ConverterStream;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @param <O> 标记，将会转换的目标类型
 * @author oneyoung* @since 2020/1/15 22:12
 */
@FunctionalInterface
public interface Converter<I, O> extends Function<I, O> {

    /**
     * 普通类使用
     *
     * @param input       输入
     * @param outputClass 输出class
     * @param <I> 输入
     * @param <O> 输出
     * @return 输出
     */
    static <I, O> O directConvert(I input, Class<O> outputClass) {
        if (input == null) {
            return null;
        }
        return ConverterStream.from(input).convert(outputClass).get();
    }


    /**
     * 普通类存在继承时，可指定父类
     *
     * @param input 输入
     * @param inputClass 输入class
     * @param outputClass 输出class
     * @param <I> 输入
     * @param <O> 输出
     * @return 输出
     */
    static <I, O> O directConvert(I input, Class<? super I> inputClass, Class<O> outputClass) {
        if (input == null) {
            return null;
        }
        return ConverterStream.from(input, inputClass).convert(outputClass).get();
    }

    /**
     * Collection集合类使用
     */
    static <Input, Collect extends Collection<Input>, Output> Collection<Output> directConvertCollection(Collect input, Class<Input> inputClass, Class<Output> outputClass) {
        if (input == null) {
            return null;
        }
        return ConverterStream.fromCollection(input, inputClass).convert(outputClass).get();
    }

    /**
     * List集合类使用
     */
    static <Input, Collect extends List<Input>, Output> List<Output> directConvertCollection(Collect input, Class<Input> inputClass, Class<Output> outputClass) {
        if (input == null) {
            return null;
        }
        return ConverterStream.fromList(input, inputClass).convert(outputClass).get();
    }

    /**
     * Collection集合类使用，遍历方式
     */
    static <Input, Collect extends Collection<Input>, Output> Collection<Output> directConvertCollectionSingle(Collect input, Class<Output> outputClass) {
        if (input == null) {
            return null;
        }
        return input.stream().map(item -> directConvert(item, outputClass)).collect(Collectors.toList());
    }

    /**
     * Collection集合类使用，遍历方式
     */
    static <Input, Collect extends Collection<Input>, Output> Collection<Output> directConvertCollectionSingle(Collect input, Class<Input> inputClass, Class<Output> outputClass) {
        if (input == null) {
            return null;
        }
        return input.stream().map(item -> directConvert(item, inputClass, outputClass)).collect(Collectors.toList());
    }

    /**
     * List集合类使用，遍历方式
     */
    static <Input, Collect extends List<Input>, Output> List<Output> directConvertCollectionSingle(Collect input, Class<Output> outputClass) {
        if (input == null) {
            return null;
        }
        return input.stream().map(item -> directConvert(item, outputClass)).collect(Collectors.toList());
    }

    /**
     * List集合类使用，遍历方式
     */
    static <I, C extends List<I>, O> List<O> directConvertCollectionSingle(C input, Class<I> inputClass, Class<O> outputClass) {
        if (input == null) {
            return null;
        }
        return input.stream().map(item -> directConvert(item, inputClass, outputClass)).collect(Collectors.toList());
    }

    /**
     * 转换对象
     *
     * @param input 输入
     * @return 输出
     */
    O convert(I input);

    /**
     * 默认
     *
     * @param input 输入
     * @return 输出
     */
    @Override
    default O apply(I input) {
        return convert(input);
    }
}
