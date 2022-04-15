package top.oenyoung.convert.sample.converter;

import org.springframework.stereotype.Component;
import top.oenyoung.convert.sample.model.CreateSchoolRequest;
import top.oenyoung.convert.sample.model.CreateSchoolServiceRequest;
import top.oneyoung.converter.Converter;

/**
 * CreateSchoolRequestToCreateSchoolServiceRequestConverter
 *
 * @author oneyoung
 * @since 2022/4/15 17:07
 */
@Component
public class CreateSchoolRequestToCreateSchoolServiceRequestConverter implements Converter<CreateSchoolRequest, CreateSchoolServiceRequest>  {

    @Override
    public CreateSchoolServiceRequest convert(CreateSchoolRequest source) {
        if (source == null) {
            return null;
        }
        System.out.println("user diy converter");
        CreateSchoolServiceRequest target = new CreateSchoolServiceRequest();
        target.setName(source.getName());
        target.setAddress(source.getAddress());
        target.setPhone(source.getPhone());
        target.setEmail(source.getEmail());
        return target;
    }
}

