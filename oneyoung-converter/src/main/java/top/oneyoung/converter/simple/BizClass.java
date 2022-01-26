package top.oneyoung.converter.simple;


import top.oneyoung.converter.Converter;
import top.oneyoung.converter.stream.ConverterStream;

import java.util.stream.Stream;

/**
 * @author oneyoung
 * @since 2020/1/15 22:44
 */
public class BizClass {
    public static void main(String[] args) {
        {
            A a = new A();
            B b = new B();
            ConverterStream<A> stream = ConverterStream.from(a);
            ConverterStream<B> convert = stream.convert(B.class);
            ConverterStream<B> merge = convert.merge(b);
            ConverterStream<B> fill = merge.fill(C.class);
            B b1 = fill.get();
        }
        {
            A a = new A();
            B b = new B();
            B b1 = ConverterStream.from(a)
                    .convert(B.class)
                    .merge(b)
                    .fill(C.class)
                    .get();
        }
        {
            A a = new A();
            B b = new B();
            C c = new C();
            B b2 = Stream.of(a)
                    .map(a1 -> new AToBConverter().convert(a1))
                    .map(b1 -> new BMergeBMerger().merge(b1, b))
                    .map(b1 -> new BFillCFiller().fill(b1, C.class))
                    .findFirst().orElse(null);
        }
        {
            A a = new A();
            B b = Converter.directConvert(a, B.class);

            Converter.directConvert(a, D.class, B.class);
        }
    }
}
