package top.oneyoung.converter.simple.batch;


import top.oneyoung.converter.Converter;
import top.oneyoung.converter.simple.A;
import top.oneyoung.converter.simple.B;

import java.util.ArrayList;
import java.util.List;

/**
 * @author oneyoung
 * @since 2020/1/16 20:00
 */
public class BatchAToBConverter implements Converter<List<A>, List<B>> {

    @Override
    public List<B> convert(List<A> as) {
        ArrayList<B> bs = new ArrayList<>(as.size());
        for (A a : as) {
            bs.add(new B());
        }
        return bs;
    }
}
