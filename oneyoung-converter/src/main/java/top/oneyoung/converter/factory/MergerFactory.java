package top.oneyoung.converter.factory;

import top.oneyoung.converter.Merger;

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
class MergerFactory extends AbstractFactory {

    static final Map<MergerCacheKey, Merger<?, ?>> MERGERS = new HashMap<>();

    @SuppressWarnings("unchecked")
    static <Input, Merge> Merger<Input, Merge> getMerger(Class<Input> inputClass, Class<Merge> outputClass, boolean collection) {
        // 如果有性能问题的话，看看能不能优化下。
        MergerCacheKey converterCacheKey = new MergerCacheKey(inputClass, outputClass, collection);

        return (Merger<Input, Merge>) Optional.ofNullable(MERGERS.get(converterCacheKey))
                .orElseThrow(() -> new UnsupportedOperationException(String.format("未找到合适的类型转换器 Merger From %s To %s, collection %s", inputClass, outputClass, collection)));
    }

    static void register(Merger<?, ?> merger) {
        Type[] interfaceActualTypeArguments = getActualTypeArguments(merger.getClass(), 1, 2);

        MergerCacheKey mergerCacheKey;

        Type type0 = interfaceActualTypeArguments[0];
        Type type1 = interfaceActualTypeArguments[1];
        if (type0 instanceof Class && type1 instanceof Class) {
            Class<?> inputClass = (Class<?>) interfaceActualTypeArguments[0];
            Class<?> fillClass = (Class<?>) interfaceActualTypeArguments[1];
            mergerCacheKey = new MergerCacheKey(inputClass, fillClass, false);
        } else if (type0 instanceof ParameterizedType && type1 instanceof ParameterizedType) {
            if (isCollection(type0)) {
                mergerCacheKey = new MergerCacheKey(getCollectionActualType(type0), getCollectionActualType(type1), true);
            } else {
                throw new UnsupportedOperationException("unsupported type " + type0 + " " + type1);
            }
        } else {
            throw new UnsupportedOperationException("unsupported type " + type0 + " " + type1);
        }
        Merger<?, ?> mergerExist = MERGERS.get(mergerCacheKey);
        if (mergerExist != null) {
            LOGGER.warn("重复定义类型转换器，后者覆盖前者。 " + mergerExist.getClass() + "," + merger.getClass());
        }
        MERGERS.put(mergerCacheKey, merger);
    }

    private static class MergerCacheKey {
        private Class<?> input;
        private Class<?> merge;
        private boolean collection;

        public MergerCacheKey(Class<?> input, Class<?> merge, boolean collection) {
            Objects.requireNonNull(input);
            Objects.requireNonNull(merge);
            this.input = input;
            this.merge = merge;
            this.collection = collection;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MergerCacheKey that = (MergerCacheKey) o;
            return collection == that.collection &&
                    Objects.equals(input, that.input) &&
                    Objects.equals(merge, that.merge);
        }

        @Override
        public int hashCode() {
            return Objects.hash(input, merge, collection);
        }
    }
}
