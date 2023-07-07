package oneyoung.designpattern.decorator;

/**
 * DataSourceDecorator
 *
 * @author oneyoung
 * @since 2022/3/23 15:13
 */
public class DataSourceDecorator implements DataSource{

    private final DataSource wrapper;

    DataSourceDecorator(DataSource source) {
        this.wrapper = source;
    }

    @Override
    public void writeData(String data) {
        wrapper.writeData(data);
    }

    @Override
    public String readData() {
        return wrapper.readData();
    }
}
