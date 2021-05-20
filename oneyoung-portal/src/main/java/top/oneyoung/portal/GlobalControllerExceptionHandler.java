package top.oneyoung.portal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;
import top.oneyoung.common.result.Result;
import top.oneyoung.common.result.Results;

import java.util.List;

/**
 * 全局异常处理
 *
 * @author oneyoung
 */

@Slf4j
@ControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * A single place to customize the response body of all exception types.
     * <p>The default implementation sets the {@link WebUtils#ERROR_EXCEPTION_ATTRIBUTE}
     * request attribute and creates a {@link ResponseEntity} from the given
     * body, headers, and status.
     *
     * @param ex      the exception
     * @param body    the body for the response
     * @param headers the headers for the response
     * @param status  the response status
     * @param request the current request
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Result<Object> result = Results.failException(ex);
        ResponseEntity<Object> resultResponseEntity = ResponseEntity.badRequest().body(result);
        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException validException = (MethodArgumentNotValidException) ex;
            BindingResult bindingResult = validException.getBindingResult();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            StringBuilder stringBuilder = new StringBuilder();
            for (FieldError error : fieldErrors) {
                String objectName = error.getObjectName();
                String field = error.getField();
                String defaultMessage = error.getDefaultMessage();
                Object rejectedValue = error.getRejectedValue();
                String message = "object '" + objectName + "' field '" + field +
                        "': rejected value [" + ObjectUtils.nullSafeToString(rejectedValue) + "]" + " message [ " + defaultMessage + " ];";
                stringBuilder.append(message);
            }
            result = Results.failIllegalArgument(stringBuilder.toString());
            resultResponseEntity = ResponseEntity.badRequest().body(result);
        }
        if (ex instanceof NoHandlerFoundException) {
            resultResponseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        log.error("request error, error result: " + result, ex);
        return resultResponseEntity;
    }
}