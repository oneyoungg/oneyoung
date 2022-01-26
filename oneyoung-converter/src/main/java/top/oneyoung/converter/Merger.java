package top.oneyoung.converter;


import top.oneyoung.converter.stream.ConverterStream;

import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;

/**
 * @author oneyoung* @since 2020/1/15 22:20
 */
@FunctionalInterface
public interface Merger<Input, Merge> extends BiFunction<Input, Merge, Input> {
    /**
     * 普通类使用
     */
    static <Input, Merge> Input directMerge(Input input, Merge merge) {
        if (input == null || merge == null) {
            return input;
        }
        return ConverterStream.from(input).merge(merge).get();
    }

    /**
     * 普通类存在继承时，可指定父类
     */
    static <Input, Merge> Input directMerge(Input input, Class<? super Input> inputClass, Merge merge) {
        if (input == null || merge == null) {
            return input;
        }
        return (Input) ConverterStream.from(input, inputClass).merge(merge).get();
    }

    /**
     * Collection集合类使用
     */
    static <Input, Collect extends Collection<Input>, Merge> Collect directMergeCollection(Collect input, Class<Input> inputClass, Merge merge) {
        if (input == null || merge == null) {
            return input;
        }
        return ConverterStream.fromCollection(input, inputClass).merge(merge).get();
    }

    /**
     * List集合类使用
     */
    static <Input, Collect extends List<Input>, Merge> Collect directMergeCollection(Collect input, Class<Input> inputClass, Merge merge) {
        if (input == null || merge == null) {
            return input;
        }
        return ConverterStream.fromList(input, inputClass).merge(merge).get();
    }

    /**
     * Collection集合类使用，遍历方式
     */
    static <Input, Collect extends Collection<Input>, Merge> Collect directMergeCollectionSingle(Collect input, Merge merge) {
        if (input == null || merge == null) {
            return input;
        }
        input.forEach(item -> {
            directMerge(item, merge);
        });
        return input;
    }

    /**
     * Collection集合类使用，遍历方式
     */
    static <Input, Collect extends Collection<Input>, Merge> Collect directMergeCollectionSingle(Collect input, Class<Input> inputClass, Merge merge) {
        if (input == null || merge == null) {
            return input;
        }
        input.forEach(item -> {
            directMerge(item, inputClass, merge);
        });
        return input;
    }

    /**
     * List集合类使用，遍历方式
     */
    static <Input, Collect extends List<Input>, Merge> Collect directMergeCollectionSingle(Collect input, Merge merge) {
        if (input == null || merge == null) {
            return input;
        }
        input.forEach(item -> {
            directMerge(item, merge);
        });
        return input;
    }

    /**
     * List集合类使用，遍历方式
     */
    static <Input, Collect extends List<Input>, Merge> Collect directMergeCollectionSingle(Collect input, Class<Input> inputClass, Merge merge) {
        if (input == null || merge == null) {
            return input;
        }
        input.forEach(item -> {
            directMerge(item, inputClass, merge);
        });
        return input;
    }

    /**
     * 转换对象
     *
     * @param input 输入
     * @param merge 待合并的对象
     * @return 输入
     */
    Input merge(Input input, Merge merge);

    @Override
    default Input apply(Input input, Merge merge) {
        return merge(input, merge);
    }
}
