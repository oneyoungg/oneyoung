package top.oneyoung.converter.stream.collection;


import top.oneyoung.converter.factory.ConverterProxy;
import top.oneyoung.converter.factory.FillerProxy;
import top.oneyoung.converter.factory.MergerProxy;
import top.oneyoung.converter.stream.AbstractPipelineConverter;

import java.util.Collection;

/**
 * @author oneyoung
 * @since 2020/1/16 12:51
 */
@SuppressWarnings("unchecked")
public class PipelineConverterCollection<Input, Collect extends Collection<Input>>
        extends AbstractPipelineConverter<Input>
        implements ConverterCollectionStream<Collect> {


    public PipelineConverterCollection(Collect input, Class<Input> inputClass) {
        super(input, inputClass);
    }

    @Override
    public <Output> ConverterCollectionStream<Collection<Output>> convert(Class<Output> outputClass) {
        Output output = new ConverterProxy<>(this.inputClass, outputClass, isCollection())
                .convert(input);
        return ConverterCollectionStream.from((Collection<Output>) output, outputClass);
    }

    @Override
    public <Merge> ConverterCollectionStream<Collect> merge(Merge merge) {
        if (merge == null) {
            return ConverterCollectionStream.from((Collect) input, inputClass);
        }
        Input input = new MergerProxy<>(this.inputClass, (Class<Merge>) merge.getClass(), isCollection())
                .merge(this.input, merge);
        return ConverterCollectionStream.from((Collect) input, inputClass);
    }

    @Override
    public <Merge> ConverterCollectionStream<Collect> merge(Merge merge, Class<Merge> mergeClass) {
        if (merge == null) {
            return ConverterCollectionStream.from((Collect) input, inputClass);
        }
        Input input = new MergerProxy<>(this.inputClass, mergeClass, isCollection())
                .merge(this.input, merge);
        return ConverterCollectionStream.from((Collect) input, inputClass);
    }

    @Override
    public <Fill> ConverterCollectionStream<Collect> fill(Class<Fill> fillClass, Object... objects) {
        Input input = new FillerProxy<>(inputClass, fillClass, isCollection())
                .fill(this.input, objects);
        return ConverterCollectionStream.from((Collect) input, inputClass);
    }

    @Override
    public Collect get() {
        return (Collect) input;
    }
}
