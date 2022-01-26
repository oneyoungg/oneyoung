package top.oneyoung.converter.simple;


import top.oneyoung.converter.ConverterGenerator;

/**
 * @author oneyoung
 * @since 2020/6/10
 */
public class TestConverterGenerator {

    public static void main(String[] args) {
        String s = ConverterGenerator.generateCode(Organization.class, Organization.class);
        System.out.println(s);
    }
}
