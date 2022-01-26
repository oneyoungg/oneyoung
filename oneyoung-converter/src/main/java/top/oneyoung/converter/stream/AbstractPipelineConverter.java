package top.oneyoung.converter.stream;

import java.util.Collection;

/**
 * @author oneyoung
 * @since 2020/1/16 12:52
 */
@SuppressWarnings("unchecked")
public abstract class AbstractPipelineConverter<I> {

    protected I input;

    protected Class<I> inputClass;

    protected AbstractPipelineConverter(I input) {
        this(input, (Class<I>) input.getClass());
    }

    protected AbstractPipelineConverter(I input, Class<I> inputClass) {
        this.input = input;
        this.inputClass = inputClass;
    }

    protected AbstractPipelineConverter(Collection<I> inputCollection, Class<I> inputClass) {
        this.input = (I) inputCollection;
        this.inputClass = inputClass;
    }

    protected boolean isCollection() {
        return input instanceof Collection;
    }
}
