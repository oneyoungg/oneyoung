package top.oneyoung.converter.simple.batch;


import top.oneyoung.converter.Filler;
import top.oneyoung.converter.simple.A;
import top.oneyoung.converter.simple.C;

import java.util.List;

/**
 * @author oneyoung
 * @since 2020/1/16 20:00
 */
public class BatchAFillCFiller implements Filler<List<A>, C> {

    @Override
    public List<A> fill(List<A> as, Object... object) {
        for (A a : as) {
            a.setC(new C());
        }
        return as;
    }
}
