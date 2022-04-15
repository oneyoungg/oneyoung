package top.oneyoung.portal.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.oneyoung.converter.Converter;
import top.oneyoung.portal.entity.Request;
import top.oneyoung.portal.entity.Response;

/**
 * RequestToResponseConverter
 *
 * @author oneyoung
 * @since 2022/4/15 22:42
 */
@Slf4j
@Component
public class RequestToResponseConverter implements Converter<Request, Response> {

    @Override
    public Response convert(Request source) {
        if (source == null) {
            return null;
        }
        log.info("RequestToResponseConverter.convert: {}", source);
        Response target = new Response();
        target.setName(source.getName());
        return target;
    }
}