package top.oneyoung.converter.factory;

import org.springframework.beans.BeanUtils;
import top.oneyoung.converter.Converter;
import top.oneyoung.converter.ConverterGenerator;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * 包级别可见，对外不可见。
 *
 * @author oneyoung
 * @since 18/10/23
 */
class ConverterFactory extends AbstractFactory {

    static final Map<ConverterCacheKey, Converter<?, ?>> CONVERTS = new HashMap<>();

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <I, O> Converter<I, O> getConverter(Class<I> inputClass, Class<O> outputClass, boolean collection) {
        // 如果有性能问题的话，看看能不能优化下。
        ConverterCacheKey converterCacheKey = new ConverterCacheKey(inputClass, outputClass, collection);

        return Optional.ofNullable(CONVERTS.get(converterCacheKey)).orElse((Converter) input -> {
            String converterCode = ConverterGenerator.generateCode(inputClass, outputClass);
            LOGGER.warn("未找到合适的类型转换器,将使用默认转换器转化。Convert From {} To {} and isCollection={}, 转换类参考转换代码： {}", inputClass, outputClass, collection, converterCode);
            O o;
            try {
                o = outputClass.newInstance();
                BeanUtils.copyProperties(input, o);
            } catch (Exception e) {
                LOGGER.error("使用默认转换器转换出错,Convert From {} To {} and isCollection={}", inputClass, outputClass, collection);
                throw new UnsupportedOperationException("不支持默认转换器，使用默认转换出错");
            }
            return o;
        });
    }

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

    static List<String> convertListString() {
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
