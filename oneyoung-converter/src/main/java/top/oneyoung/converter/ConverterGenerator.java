package top.oneyoung.converter;


import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;
import top.oneyoung.converter.model.ClassInfo;
import top.oneyoung.converter.util.ClassUtil;
import top.oneyoung.converter.util.StringUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @author oneyoung
 */
public class ConverterGenerator {

    private static final String ENTER = "\r\n";
    private static final String TARGET_SET_PREFIX = "target.";
    private static final String SOURCE_GET_PREFIX = "source.";
    private static final String TABLE = "    ";

    private ConverterGenerator() {
    }

    public static String generateCode(Class<?> source, Class<?> target) {
        checkArgument(source, target);
        String importConverterStatement = generateImportStatement(Arrays.asList(Converter.class, source, target));
        String converterClassName = source.getSimpleName() + "To" + target.getSimpleName() + "Converter implements Converter<" + source.getSimpleName() + ", " + target.getSimpleName() + "> ";
        String methodName = "convert";

        return importConverterStatement
                + "import org.springframework.stereotype.Component;" + ENTER
                + ENTER
                + "@Component"
                + ENTER
                + "public class " + converterClassName + " {" + ENTER
                + ENTER
                + TABLE + "@Override"
                + ENTER
                + TABLE + "public " + target.getSimpleName() + " " + methodName + "(" + source.getSimpleName() + " source) {" + ENTER
                + generateMethodContent(source, target) + ENTER
                + TABLE + "}" + ENTER
                + "}";
    }

    /**
     * 生产原始类到目标类的方法转换内容
     */
    private static String generateMethodContent(Class<?> source, Class<?> target) {
        ClassInfo sourceClassInfo = ClassUtil.getClassInfo(source);
        ClassInfo targetClassInfo = ClassUtil.getClassInfo(target);
        List<Field> targetFields = targetClassInfo.getFields();
        Map<String, Field> sourceTargetMap = sourceClassInfo.getFieldMap();
        StringBuilder fieldGetAndSetBuilder = new StringBuilder();
        fieldGetAndSetBuilder.append(TABLE).append(TABLE).append("if (source == null) {").append(ENTER)
                .append(TABLE).append(TABLE).append(TABLE).append("return null;").append(ENTER)
                .append(TABLE).append(TABLE).append("}").append(ENTER);
        fieldGetAndSetBuilder.append(TABLE).append(TABLE).append(target.getSimpleName()).append(" target = new ").append(target.getSimpleName()).append("();").append(ENTER);
        for (Field targetField : targetFields) {
            if (Modifier.isStatic(targetField.getModifiers())) {
                continue;
            }
            if (canUseBaseConverter(targetField)) {
                fieldGetAndSetBuilder
                        .append(TABLE).append(TABLE)
                        .append(TARGET_SET_PREFIX)
                        .append(StringUtil.getSetMethodName(targetField.getName()))
                        .append("(").append(buildSourceFieldGetStatement(targetField, sourceTargetMap)).append(")")
                        .append(";")
                        .append(ENTER);
            } else {
                fieldGetAndSetBuilder
                        .append(TABLE).append(TABLE)
                        .append(TARGET_SET_PREFIX)
                        .append(StringUtil.getSetMethodName(targetField.getName()))
                        .append("(").append(buildSourceFieldSubConverter(targetField, sourceTargetMap)).append(")")
                        .append(";")
                        .append(ENTER);
            }
        }
        fieldGetAndSetBuilder.append(TABLE)
                .append(TABLE).append("System.out.println(\"from auto generate\");")
                .append(TABLE).append("return target;");
        return fieldGetAndSetBuilder.toString();
    }

    /**
     * 如果目标字段是JAVA类型，则直接生成target.setXxx(source.getXxx());
     */
    private static String buildSourceFieldGetStatement(Field targetField, Map<String, Field> sourceTargetMap) {
        return Optional.ofNullable(sourceTargetMap.get(targetField.getName())).map(Field::getName).map(StringUtil::getGetMethodName).map(it -> SOURCE_GET_PREFIX + it + "()").orElse("");
    }

    /**
     * 如果目标字段是非JAVA类型，则需要生成Converter.directConvert嵌套转换
     */
    private static String buildSourceFieldSubConverter(Field targetField, Map<String, Field> sourceTargetMap) {
        Class<?> type = targetField.getType();
        if (Collection.class.isAssignableFrom(type)) {
            Type[] actualTypeArguments = ((ParameterizedTypeImpl) targetField.getGenericType()).getActualTypeArguments();
            Type actualTypeArgument = actualTypeArguments[0];
            String typeName = actualTypeArgument.getTypeName();
            return String.format("Converter.directConvertCollectionSingle(%s, %s)", Optional.ofNullable(sourceTargetMap.get(targetField.getName())).map(Field::getName).map(StringUtil::getGetMethodName).map(it -> SOURCE_GET_PREFIX + it + "()").orElse(""), typeName.substring(typeName.lastIndexOf('.') + 1).replace('$', '.') + ".class");
        } else {
            return String.format("Converter.directConvert(%s, %s)", Optional.ofNullable(sourceTargetMap.get(targetField.getName())).map(Field::getName).map(StringUtil::getGetMethodName).map(it -> SOURCE_GET_PREFIX + it + "()").orElse(""), targetField.getType().getSimpleName() + ".class");
        }
    }

    /**
     * 生成导入语句 import xxx;
     *
     * @param classList 类
     * @return 导入语句
     */
    private static String generateImportStatement(List<Class<?>> classList) {
        StringBuilder importStatementBuilder = new StringBuilder();
        for (Class<?> clazz : classList) {
            importStatementBuilder.append("import ").append(clazz.getCanonicalName()).append(";").append(ENTER);
        }
        return importStatementBuilder.toString();
    }


    /**
     * 入参不能为空
     */
    private static void checkArgument(Class<?> source, Class<?> target) {
        if (source == null) {
            throw new IllegalArgumentException("source cannot be null");
        }
        if (target == null) {
            throw new IllegalArgumentException("target cannot be null");
        }
    }

    /**
     * JAVA内部的类都可以直接转
     */
    private static boolean canUseBaseConverter(Field targetField) {
        Class<?> type = targetField.getType();
        Type genericType = targetField.getGenericType();
        if (genericType instanceof ParameterizedTypeImpl) {
            Type[] actualTypeArguments = ((ParameterizedTypeImpl) targetField.getGenericType()).getActualTypeArguments();
            for (Type actualTypeArgument : actualTypeArguments) {
                if (!isPrimitiveAndBasic(actualTypeArgument)) {
                    return false;
                }
            }
            return true;
        } else {
            return isPrimitiveAndBasic(type) && !(Collection.class.isAssignableFrom(type));
        }
    }

    /**
     * 是否是JAVA的类
     */
    private static boolean isPrimitiveAndBasic(Type type) {
        return type.getTypeName().startsWith("java.") || type.getTypeName().startsWith("javax.") || type.getTypeName().endsWith("SDO");
    }

    /**
     * 是否是JAVA的类
     */
    private static boolean isPrimitiveAndBasic(Class<?> type) {
        return type.isPrimitive() || type.getName().startsWith("java.") || type.getName().startsWith("javax.") || type.getTypeName().endsWith("SDO");
    }
}
