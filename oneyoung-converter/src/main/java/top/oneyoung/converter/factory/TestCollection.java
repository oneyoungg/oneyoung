package top.oneyoung.converter.factory;


import top.oneyoung.converter.simple.A;
import top.oneyoung.converter.simple.B;
import top.oneyoung.converter.simple.C;
import top.oneyoung.converter.simple.D;
import top.oneyoung.converter.simple.batch.BatchAFillCFiller;
import top.oneyoung.converter.simple.batch.BatchAToBConverter;
import top.oneyoung.converter.simple.batch.BatchBFillCFiller;
import top.oneyoung.converter.stream.ConverterStream;
import top.oneyoung.converter.stream.collection.ConverterCollectionStream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author oneyoung
 * @since 2020/1/16 20:00
 */
public class TestCollection {
    public static void main(String[] args) {
        FillerFactory.register(new BatchAFillCFiller());
        FillerFactory.register(new BatchBFillCFiller());
        ConverterFactory.register(new BatchAToBConverter());
        List<A> aList = new ArrayList<>();
        aList.add(new A());
        aList.add(new A());
        aList.add(new A());
        {
            ConverterCollectionStream<Collection<B>> convert = ConverterStream
                    .fromCollection(aList, A.class)
                    .fill(C.class)
                    .convert(B.class);
            ConverterCollectionStream<Collection<C>> convert1 = convert.convert(C.class);
            ConverterCollectionStream<Collection<D>> convert2 = convert1.convert(D.class);
        }
        {
            Collection<B> collections = ConverterStream
                    .fromCollection(aList, A.class)
                    .fill(C.class)
                    .convert(B.class)
                    .fill(C.class)
                    .get();
        }
        {
            Stream<List<A>> aList1 = Stream.of(aList);
            Stream<List<A>> listStream = aList1.map(new Function<List<A>, List<A>>() {
                @Override
                public List<A> apply(List<A> item) {
                    return new BatchAFillCFiller().fill(item, C.class);
                }
            });
        }
        {
            Collection<B> bs = ConverterStream
                    .fromList(aList, A.class)
                    .fill(C.class)
                    .convert(B.class)
                    .fill(C.class)
                    .get();
        }
    }
}
