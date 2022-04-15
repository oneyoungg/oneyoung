package top.oneyoung.converter.factory;


import top.oneyoung.converter.Converter;

/**
 * 一个默认代理
 *
 * @author oneyoung
 * @since 2020/1/15 22:31
 */
public class ConverterProxy<I, O> implements Converter<I, O> {

    private final Converter<I, O> delegate;

    private static final boolean AUTO_CONVERT = true;

    public ConverterProxy(Class<I> inputClass, Class<O> outputClass, boolean collection) {
        this.delegate = ConverterFactory.getConverter(inputClass, outputClass, collection, AUTO_CONVERT);
    }

    @Override
    public O convert(I input) {
        return delegate.convert(input);
    }
}
