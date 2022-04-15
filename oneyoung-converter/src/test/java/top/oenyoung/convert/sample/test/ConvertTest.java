package top.oenyoung.convert.sample.test;

import net.openhft.compiler.CompilerUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import top.oenyoung.convert.sample.converter.CreateSchoolRequestToCreateSchoolServiceRequestConverter;
import top.oenyoung.convert.sample.model.CreateSchoolRequest;
import top.oenyoung.convert.sample.model.CreateSchoolServiceRequest;
import top.oneyoung.converter.Converter;
import top.oneyoung.converter.ConverterGenerator;
import top.oneyoung.converter.factory.ConverterFactory;

import java.util.Arrays;
import java.util.List;

/**
 * ConvertTest
 *
 * @author oneyoung
 * @since 2022/4/15 17:12
 */
@RunWith(JUnit4.class)
public class ConvertTest {

    private static void convert() {
        CreateSchoolRequest input = CreateSchoolRequest.builder()
                .name("test1")
                .address("test address1")
                .build();
        CreateSchoolRequest input1 = CreateSchoolRequest.builder()
                .name("test2")
                .address("test address2")
                .build();
        List<CreateSchoolRequest> createSchoolRequests = Arrays.asList(input, input1);
        List<CreateSchoolServiceRequest> createSchoolServiceRequests = Converter.directConvertCollectionSingle(createSchoolRequests, CreateSchoolServiceRequest.class);
        System.out.println(createSchoolServiceRequests);
    }

    @Test
    public void test1() {
        ConverterFactory.register(new CreateSchoolRequestToCreateSchoolServiceRequestConverter());
        convert();
    }

    @Test
    public void test2() {
        String code = ConverterGenerator.generateCode(CreateSchoolRequest.class, CreateSchoolServiceRequest.class);
        String name = CreateSchoolRequest.class.getSimpleName() + "To" + CreateSchoolServiceRequest.class.getSimpleName() + "Converter";
        try {
            Class aClass = CompilerUtils.CACHED_COMPILER.loadFromJava(name, code);
            ConverterFactory.register((Converter<?, ?>) aClass.newInstance());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        convert();
    }

    @Test
    public void name() {
        Converter.directConvert(CreateSchoolRequest.builder().name("test").build(), CreateSchoolServiceRequest.class);
    }
}
