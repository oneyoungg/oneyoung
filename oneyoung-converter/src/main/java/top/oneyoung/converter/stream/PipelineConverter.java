package top.oneyoung.converter.stream;

import top.oneyoung.converter.factory.ConverterProxy;
import top.oneyoung.converter.factory.FillerProxy;
import top.oneyoung.converter.factory.MergerProxy;

import java.util.Collection;

/**
 * @author oneyoung
 * @since 2020/1/15 22:33
 */
@SuppressWarnings("unchecked")
public class PipelineConverter<I> extends AbstractPipelineConverter<I> implements ConverterStream<I> {

    public PipelineConverter(I input) {
        super(input);
    }

    public PipelineConverter(I input, Class<I> inputClass) {
        super(input, inputClass);
    }

    public PipelineConverter(Collection<I> input, Class<I> inputClass) {
        super(input, inputClass);
    }

    @Override
    public <O> ConverterStream<O> convert(Class<O> outputClass) {
        O output = new ConverterProxy<>(this.inputClass, outputClass, isCollection())
                .convert(input);
        return ConverterStream.from(output);
    }

    @Override
    public <M> ConverterStream<I> merge(M merge) {
        if (merge == null) {
            return ConverterStream.from(input);
        }
        I input = new MergerProxy<>(this.inputClass, (Class<M>) merge.getClass(), isCollection())
                .merge(this.input, merge);
        return ConverterStream.from(input);
    }

    @Override
    public <M> ConverterStream<I> merge(M merge, Class<M> mergeClass) {
        if (merge == null) {
            return ConverterStream.from(input);
        }
        I input = new MergerProxy<>(this.inputClass, mergeClass, isCollection())
                .merge(this.input, merge);
        return ConverterStream.from(input);
    }

    @Override
    public <F> ConverterStream<I> fill(Class<F> fillClass, Object... objects) {
        I input = new FillerProxy<>(inputClass, fillClass, isCollection())
                .fill(this.input, objects);
        return ConverterStream.from(input);
    }

    @Override
    public I get() {
        return input;
    }
}
