package top.oneyoung.converter.factory;

import top.oneyoung.converter.Filler;

/**
 * 一个默认代理
 *
 * @author oneyoung
 * @since 2020/1/15 22:33
 */
public class FillerProxy<Input, Fill> implements Filler<Input, Fill> {

    private Filler<Input, Fill> delegate;

    public FillerProxy(Class<Input> inputClass, Class<Fill> fillClass, boolean collection) {
        delegate = FillerFactory.getFiller(inputClass, fillClass, collection);
    }

    @Override
    public Input fill(Input input, Object... objects) {
        return delegate.fill(input, objects);
    }
}
