package top.oneyoung.converter.stream.collection;


import top.oneyoung.converter.factory.ConverterProxy;
import top.oneyoung.converter.factory.FillerProxy;
import top.oneyoung.converter.factory.MergerProxy;
import top.oneyoung.converter.stream.AbstractPipelineConverter;

import java.util.List;

/**
 * @author oneyoung* @since 2020/1/16 12:51
 */
@SuppressWarnings("unchecked")
public class PipelineConverterList<Input, Collect extends List<Input>>
        extends AbstractPipelineConverter<Input>
        implements ConverterListStream<Collect> {


    public PipelineConverterList(Collect input, Class<Input> inputClass) {
        super(input, inputClass);
    }

    @Override
    public <Output> ConverterListStream<List<Output>> convert(Class<Output> outputClass) {
        Output output = new ConverterProxy<>(this.inputClass, outputClass, isCollection())
                .convert(input);
        return ConverterListStream.from((List<Output>) output, outputClass);
    }

    @Override
    public <Merge> ConverterListStream<Collect> merge(Merge merge) {
        if (merge == null) {
            return ConverterListStream.from((Collect) input, inputClass);
        }
        Input input = new MergerProxy<>(this.inputClass, (Class<Merge>) merge.getClass(), isCollection())
                .merge(this.input, merge);
        return ConverterListStream.from((Collect) input, inputClass);
    }

    @Override
    public <Merge> ConverterListStream<Collect> merge(Merge merge, Class<Merge> mergeClass) {
        if (merge == null) {
            return ConverterListStream.from((Collect) input, inputClass);
        }
        Input input = new MergerProxy<>(this.inputClass, mergeClass, isCollection())
                .merge(this.input, merge);
        return ConverterListStream.from((Collect) input, inputClass);
    }

    @Override
    public <Fill> ConverterListStream<Collect> fill(Class<Fill> fillClass, Object... objects) {
        Input input = new FillerProxy<>(inputClass, fillClass, isCollection())
                .fill(this.input, objects);
        return ConverterListStream.from((Collect) input, inputClass);
    }

    @Override
    public Collect get() {
        return (Collect) input;
    }
}
