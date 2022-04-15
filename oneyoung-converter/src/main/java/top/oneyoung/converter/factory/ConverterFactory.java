package top.oneyoung.converter.factory;

import net.openhft.compiler.CompilerUtils;
import top.oneyoung.converter.Converter;
import top.oneyoung.converter.ConverterGenerator;
import top.oneyoung.converter.exception.ConvertException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * 转换器工厂
 *
 * @author oneyoung
 * @since 18/10/23
 */
public class ConverterFactory extends AbstractFactory {

    static final Map<ConverterCacheKey, Converter<?, ?>> CONVERTS = new HashMap<>();

    /**
     * 是否自动转换
     */
    public static boolean autoConvert = false;

    /**
     * 获取转换器
     *
     * @param inputClass  输入类型
     * @param outputClass 输出类型
     * @param collection  是否是集合
     * @param <I>         输入类型
     * @param <O>         输出类型
     * @return 转换器
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <I, O> Converter<I, O> getConverter(Class<I> inputClass, Class<O> outputClass, boolean collection) {
        ConverterCacheKey converterCacheKey = new ConverterCacheKey(inputClass, outputClass, collection);
        // 尝试获取转换器
        Converter<I, O> converter = (Converter<I, O>) CONVERTS.get(converterCacheKey);
        // 开启了自动转换
        if (autoConvert) {
            converter = Optional.ofNullable(converter).orElseGet(() -> {
                // 找不到转换器，尝试自动生成
                String code = ConverterGenerator.generateCode(inputClass, outputClass);
                String name = inputClass.getSimpleName() + "To" + outputClass.getSimpleName() + "Converter";
                try {
                    // 编译代码 并加载类
                    Class aClass = CompilerUtils.CACHED_COMPILER.loadFromJava(name, code);
                    Converter<I, O> autoConverter = (Converter<I, O>) aClass.newInstance();
                    // 注册自动生成的转换器
                    ConverterFactory.register(autoConverter);
                    LOGGER.info("自动生成了类型转换器，并动态加载到JVM，注册为转换器。类名：{} \n code : \n{}", name, code);
                    return autoConverter;
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                    LOGGER.error(" {}  to {}  自动生成转换器失败，请手动生成转换器！", inputClass.getSimpleName(), outputClass.getSimpleName());
                    throw new ConvertException("自动生成转换器失败，请手动生成转换器！", e);
                }
            });
        } else {
            converter = Optional.ofNullable(converter).orElseThrow(() -> new ConvertException("未找到类型转换器，请检查是否已经注册。" + converterCacheKey));
        }
        return converter;
    }

    /**
     * 注册转换器
     *
     * @param converter 转换器
     */
    public static void register(Converter<?, ?> converter) {
        Type[] interfaceActualTypeArguments = getActualTypeArguments(converter.getClass(), 1, 2);

        ConverterCacheKey converterCacheKey;

        Type type0 = interfaceActualTypeArguments[0];
        Type type1 = interfaceActualTypeArguments[1];
        if (type0 instanceof Class && type1 instanceof Class) {
            Class<?> inputClass = (Class<?>) interfaceActualTypeArguments[0];
            Class<?> fillClass = (Class<?>) interfaceActualTypeArguments[1];
            converterCacheKey = new ConverterCacheKey(inputClass, fillClass, false);
        } else if (type0 instanceof ParameterizedType && type1 instanceof ParameterizedType) {
            if (isCollection(type0)) {
                converterCacheKey = new ConverterCacheKey(getCollectionActualType(type0), getCollectionActualType(type1), true);
            } else {
                throw new UnsupportedOperationException("unsupported type " + type0 + " " + type1);
            }
        } else {
            throw new UnsupportedOperationException("unsupported type " + type0 + " " + type1);
        }
        Converter<?, ?> converterExist = CONVERTS.get(converterCacheKey);
        if (converterExist != null) {
            LOGGER.warn("重复定义类型转换器，后者覆盖前者。 {}, {}", converterExist.getClass(), converter.getClass());
        }
        CONVERTS.put(converterCacheKey, converter);
    }

    public static List<String> convertListString() {
        List<String> list = new ArrayList<>(CONVERTS.size());
        CONVERTS.forEach((k, v) -> {
            if (k.collection) {
                list.add("Collection[" + k.input + "]-->" + k.output + " By " + v.getClass());
            } else {
                list.add(k.input + "-->" + k.output + " By " + v.getClass());
            }

        });
        return list;
    }

    public static void setAutoConvert(boolean autoConvert) {
        ConverterFactory.autoConvert = autoConvert;
    }

    public static boolean isAutoConvert() {
        return ConverterFactory.autoConvert;
    }

    /**
     * 缓存key
     */
    private static class ConverterCacheKey {
        private final Class<?> input;
        private final Class<?> output;
        private final boolean collection;

        public ConverterCacheKey(Class<?> input, Class<?> output, boolean collection) {
            Objects.requireNonNull(input);
            Objects.requireNonNull(output);
            this.input = input;
            this.output = output;
            this.collection = collection;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            ConverterCacheKey that = (ConverterCacheKey) o;
            return collection == that.collection &&
                    Objects.equals(input, that.input) &&
                    Objects.equals(output, that.output);
        }

        @Override
        public int hashCode() {
            return Objects.hash(input, output, collection);
        }

        @Override
        public String toString() {
            return "ConverterCacheKey{" +
                    "input=" + input +
                    ", output=" + output +
                    ", collection=" + collection +
                    '}';
        }
    }
}
