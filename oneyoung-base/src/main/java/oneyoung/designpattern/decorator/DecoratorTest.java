package oneyoung.designpattern.decorator;

/**
 * DecoratorTest
 *
 * @author oneyoung
 * @since 2022/3/23 15:21
 */
public class DecoratorTest {

    public static void main(String[] args) {
        String salaryRecords = "装饰模式真厉害啊";
        DataSource encoded = new CompressionDecorator(
                new EncryptionDecorator(
                        new FileDataSource("./target/OutputDemo.txt")
                )
        );
//        DataSource encoded = new FileDataSource("./target/OutputDemo.txt");
        encoded.writeData(salaryRecords);
        DataSource plain = new FileDataSource("./target/OutputDemo.txt");

        System.out.println("- Input ----------------");
        System.out.println(salaryRecords);
        System.out.println("- Encoded --------------");
        System.out.println(plain.readData());
        System.out.println("- Decoded --------------");
        System.out.println(encoded.readData());
    }
}
