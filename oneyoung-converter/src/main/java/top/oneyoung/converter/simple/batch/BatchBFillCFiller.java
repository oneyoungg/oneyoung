package top.oneyoung.converter.simple.batch;


import top.oneyoung.converter.Filler;
import top.oneyoung.converter.simple.B;
import top.oneyoung.converter.simple.C;

import java.util.List;

/**
 * @author oneyoung
 * @since 2020/1/16 20:00
 */
public class BatchBFillCFiller implements Filler<List<B>, C> {

    @Override
    public List<B> fill(List<B> as, Object... object) {
        for (B a : as) {
            a.setC(new C());
        }
        return as;
    }
}
