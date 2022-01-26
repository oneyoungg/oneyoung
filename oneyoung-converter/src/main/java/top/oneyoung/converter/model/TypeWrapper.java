package top.oneyoung.converter.model;

import java.lang.reflect.Type;

/**
 * @author oneyoung
 * @since 2020/1/4
 */
public final class TypeWrapper {

    private final Class<?> cls;
    private final Type[] genericType;
    private final Boolean isCollection;

    public TypeWrapper(Class<?> cls, Type[] genericType, Boolean isCollection) {
        this.cls = cls;
        this.genericType = genericType.clone();
        this.isCollection = isCollection;
    }

    public Class<?> getCls() {
        return cls;
    }

    public Type[] getGenericType() {
        return genericType.clone();
    }

    public Boolean isCollection() {
        return isCollection;
    }
}
