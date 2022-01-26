package top.oneyoung.converter.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.oneyoung.converter.model.TypeWrapper;

import java.lang.reflect.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author oneyoung
 * @date 2020/05/9
 */
public final class ReflectionUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);

    private ReflectionUtil() {
    }

    public static <T> T newInstance(Class<T> cls) {
        Object instance;
        try {
            instance = cls.newInstance();
        } catch (Exception e) {
            LOGGER.error("new instance failure", e);
            throw new RuntimeException(e);
        }
        return (T) instance;
    }

    public static Object invokeMethod(Object object, Method method, Object... args) {
        Object result;
        try {
            method.setAccessible(true);
            result = method.invoke(object, args);
        } catch (Exception e) {
            LOGGER.error("invoke method failure", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    public static void setField(Object object, Field field, Object value) {
        try {
            field.setAccessible(true);
            field.set(object, value);
        } catch (Exception e) {
            LOGGER.error("set field failure", e);
            throw new RuntimeException(e);
        }
    }

    public static Object getField(Field field, Object object) {
        field.setAccessible(true);
        try {
            return field.get(object);
        } catch (IllegalAccessException e) {
            LOGGER.error("get field failure", e);
            throw new RuntimeException(e);
        }
    }

    public static List<Field> getFields(Class clazz) {
        List<Field> result = new ArrayList<>(Arrays.asList(clazz.getDeclaredFields()));
        Optional.ofNullable(clazz.getSuperclass()).ifPresent(addSuperclassFields(result));
        return result;
    }

    public static List<Field> getNotFinalFields(Class clazz) {
        List<Field> fields = getFields(clazz);
        return fields.stream().filter(field -> !Modifier.isFinal(field.getModifiers())).collect(Collectors.toList());
    }

    private static Consumer<Class> addSuperclassFields(List<Field> result) {
        return superClass -> {
            List<Field> superclassFields = getFields(superClass);
            List<String> resultNames = result.stream().map(Field::getName).collect(Collectors.toList());
            List<Field> validSuperclassFields = superclassFields.stream().filter(superclassField -> !resultNames.contains(superclassField.getName())).collect(Collectors.toList());
            result.addAll(validSuperclassFields);
        };
    }

    public static TypeWrapper typeParse(Type type) {
        Class<?> cls = type instanceof ParameterizedType
                ? (Class<?>) ((ParameterizedType) type).getRawType()
                : (Class) type;
        return Collection.class.isAssignableFrom(cls)
                ? new TypeWrapper(getRawClass(type), getActualTypeArguments(type), Boolean.TRUE)
                : new TypeWrapper(cls, new Type[0], Boolean.FALSE);
    }

    public static Type[] getActualTypeArguments(Type type) {
        return ((ParameterizedType) type).getActualTypeArguments();
    }

    /**
     * ParameterizedType类型的Type获取外层类型
     * 如：传入List<User>，返回List
     *
     * @param type
     * @return
     */
    public static Class<?> getRawClass(Type type) {
        return (Class<?>) ((ParameterizedType) type).getRawType();
    }


    /**
     * 检查是否是基本类型或其包装类型
     *
     * @param type
     * @return
     */
    public static boolean isPrimitive(Class<?> type) {
        return type.isPrimitive()
                || type.equals(Integer.class)
                || type.equals(String.class)
                || type.equals(Long.class)
                || type.equals(Double.class)
                || type.equals(Byte.class)
                || type.equals(Short.class)
                || type.equals(Float.class)
                || type.equals(Character.class);
    }
}
