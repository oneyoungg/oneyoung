package top.oneyoung.converter.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

/**
 * 获取接口上声明的类型（这里能获取到最终实现类上面定义的类，当然，不是泛型定义时的原始类型）
 *
 * @author oneyoung
 * @since 2021/11/29 6:04 下午
 */
public abstract class AbstractFactory {

    protected static final Logger LOGGER = LoggerFactory.getLogger("top.oneyoung.converter.Converter");

    protected AbstractFactory() {
    }

    /**
     * 获取接口上定义的泛型，只支持一个接口，Input和Output两个泛型
     *
     * @return 两个泛型，Input和Output
     */
    public static Type[] getActualTypeArguments(Class<?> cls, int supportInterfaceNum, int supportGenericNum) {
        Type[] genericInterfaces = cls.getGenericInterfaces();
        if (genericInterfaces.length != supportInterfaceNum) {
            throw new UnsupportedOperationException("genericInterfaces length is invalid : " + genericInterfaces.length);
        }
        Type[] actualTypeArguments = getActualTypeArguments(genericInterfaces[0]);
        if (actualTypeArguments.length != supportGenericNum) {
            throw new UnsupportedOperationException("actualTypeArguments length is invalid : " + actualTypeArguments.length);
        }
        return actualTypeArguments;
    }

    public static Type[] getActualTypeArguments(Type type) {
        return ((ParameterizedType) type).getActualTypeArguments();
    }

    public static boolean isCollection(Type type) {
        return type instanceof ParameterizedType
                && ((ParameterizedType) type).getRawType() instanceof Class
                && Collection.class.isAssignableFrom((Class<?>) ((ParameterizedType) type).getRawType());
    }

    public static Class<?> getCollectionActualType(Type type) {
        if (isCollection(type)) {
            return (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[0];
        }
        return null;
    }

}
