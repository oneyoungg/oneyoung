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

    public ConverterProxy(Class<I> inputClass, Class<O> outputClass, boolean collection) {
        this.delegate = ConverterFactory.getConverter(inputClass, outputClass, collection);
    }

    @Override
    public O convert(I input) {
        return delegate.convert(input);
    }
}
