package top.oneyoung.converter.factory;

import top.oneyoung.converter.Merger;

/**
 * 一个默认代理
 *
 * @author oneyoung
 * @since 2020/1/15 22:34
 */
public class MergerProxy<Input, Merge> implements Merger<Input, Merge> {

    private Merger<Input, Merge> delegate;

    public MergerProxy(Class<Input> inputClass, Class<Merge> mergeClass, boolean collection) {
        delegate = MergerFactory.getMerger(inputClass, mergeClass, collection);
    }

    @Override
    public Input merge(Input input, Merge merge) {
        return delegate.merge(input, merge);
    }
}
