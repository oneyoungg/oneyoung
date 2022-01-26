package top.oneyoung.converter;


import top.oneyoung.converter.stream.ConverterStream;

import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;

/**
 * @param <Fill> 标记，需要填充的类型，将会在FillerFactory中使用，实际填充方法不包含
 * @author oneyoung
 * @since 2020/1/15 22:28
 */
@FunctionalInterface
public interface Filler<Input, Fill> extends BiFunction<Input, Object[], Input> {
    /**
     * 普通类使用
     */
    static <Input, Fill> Input directFill(Input input, Class<Fill> fill, Object... objects) {
        if (input == null || fill == null) {
            return input;
        }
        return ConverterStream.from(input).fill(fill, objects).get();
    }

    /**
     * 普通类存在继承时，可指定父类
     */
    static <Input, Fill> Input directFill(Input input, Class<Input> inputClass, Class<Fill> fill, Object... objects) {
        if (input == null || fill == null) {
            return input;
        }
        return ConverterStream.from(input, inputClass).fill(fill, objects).get();
    }

    /**
     * Collection集合类使用
     */
    static <Input, Collect extends Collection<Input>, Fill> Collect directFillCollection(Collect input, Class<Input> inputClass, Class<Fill> fill, Object... objects) {
        if (input == null || fill == null) {
            return input;
        }
        return ConverterStream.fromCollection(input, inputClass).fill(fill, objects).get();
    }

    /**
     * List集合类使用
     */
    static <Input, Collect extends List<Input>, Fill> Collect directFillCollection(Collect input, Class<Input> inputClass, Class<Fill> fill, Object... objects) {
        if (input == null || fill == null) {
            return input;
        }
        return ConverterStream.fromList(input, inputClass).fill(fill, objects).get();
    }

    /**
     * Collection集合类使用，遍历方式
     */
    static <Input, Collect extends Collection<Input>, Fill> Collect directFillCollectionSingle(Collect input, Class<Fill> fill, Object... objects) {
        if (input == null || fill == null) {
            return input;
        }
        input.forEach(item -> directFill(item, fill));
        return input;
    }

    /**
     * Collection集合类使用，遍历方式
     */
    static <Input, Collect extends Collection<Input>, Fill> Collect directFillCollectionSingle(Collect input, Class<Input> inputClass, Class<Fill> fill, Object... objects) {
        if (input == null || fill == null) {
            return input;
        }
        input.forEach(item -> directFill(item, fill));
        return input;
    }

    /**
     * List集合类使用，遍历方式
     */
    static <Input, Collect extends List<Input>, Fill> Collect directFillCollectionSingle(Collect input, Class<Fill> fill, Object... objects) {
        if (input == null || fill == null) {
            return input;
        }
        input.forEach(item -> directFill(item, fill));
        return input;
    }

    /**
     * List集合类使用，遍历方式
     */
    static <Input, Collect extends List<Input>, Fill> Collect directFillCollectionSingle(Collect input, Class<Input> inputClass, Class<Fill> fill, Object... objects) {
        if (input == null || fill == null) {
            return input;
        }
        input.forEach(item -> directFill(item, inputClass, fill));
        return input;
    }

    /**
     * 填充对象
     *
     * @param input 输入类，被填充的类
     * @return
     */
    Input fill(Input input, Object... objects);

    @Override
    default Input apply(Input input, Object[] objects) {
        return fill(input, objects);
    }
}
