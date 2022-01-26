package top.oneyoung.converter.factory;


import top.oneyoung.converter.Filler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * 包级别可见，对外不可见。
 *
 * @author oneyoung
 * @since 18/10/23
 */
class FillerFactory extends AbstractFactory {

    static final Map<FillerCacheKey, Filler<?, ?>> FILLERS = new HashMap<>();

    @SuppressWarnings("unchecked")
    static <Input, Fill> Filler<Input, Fill> getFiller(Class<Input> inputClass, Class<Fill> outputClass) {
        return getFiller(inputClass, outputClass, false);
    }

    @SuppressWarnings("unchecked")
    static <Input, Fill> Filler<Input, Fill> getFiller(Class<Input> inputClass, Class<Fill> outputClass, boolean collection) {
        // 如果有性能问题的话，看看能不能优化下。
        FillerCacheKey converterCacheKey = new FillerCacheKey(inputClass, outputClass, collection);

        return (Filler<Input, Fill>) Optional.ofNullable(FILLERS.get(converterCacheKey))
                .orElseThrow(() -> new UnsupportedOperationException(String.format("未找到合适的类型转换器 Filler From %s To %s, collection %s", inputClass, outputClass, collection)));
    }

    static void register(Filler<?, ?> filler) {
        Type[] interfaceActualTypeArguments = getActualTypeArguments(filler.getClass(), 1, 2);

        FillerCacheKey fillerCacheKey;

        Type type0 = interfaceActualTypeArguments[0];
        Type type1 = interfaceActualTypeArguments[1];
        if (type0 instanceof Class && type1 instanceof Class) {
            Class<?> inputClass = (Class<?>) interfaceActualTypeArguments[0];
            Class<?> fillClass = (Class<?>) interfaceActualTypeArguments[1];
            fillerCacheKey = new FillerCacheKey(inputClass, fillClass, false);
        } else if (type0 instanceof ParameterizedType && type1 instanceof Class) {
            if (isCollection(type0)) {
                fillerCacheKey = new FillerCacheKey(getCollectionActualType(type0), (Class<?>) type1, true);
            } else {
                throw new UnsupportedOperationException("unsupported type " + type0 + " " + type1);
            }
        } else {
            throw new UnsupportedOperationException("unsupported type " + type0 + " " + type1);
        }
        Filler<?, ?> converterExist = FILLERS.get(fillerCacheKey);
        if (converterExist != null) {
            LOGGER.warn("重复定义类型转换器，后者覆盖前者。 " + converterExist.getClass() + "," + filler.getClass());
        }
        FILLERS.put(fillerCacheKey, filler);
    }

    private static class FillerCacheKey {
        private Class<?> input;
        private Class<?> fill;
        /**
         * 输入是集合
         */
        private boolean collection;

        public FillerCacheKey(Class<?> input, Class<?> fill, boolean collection) {
            this.input = input;
            this.fill = fill;
            this.collection = collection;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            FillerCacheKey that = (FillerCacheKey) o;
            return collection == that.collection &&
                    Objects.equals(input, that.input) &&
                    Objects.equals(fill, that.fill);
        }

        @Override
        public int hashCode() {
            return Objects.hash(input, fill, collection);
        }
    }
}
