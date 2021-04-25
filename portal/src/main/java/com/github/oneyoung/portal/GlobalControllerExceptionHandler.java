//package com.github.oneyoung.portal;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.CollectionUtils;
//import org.springframework.validation.BindException;
//import org.springframework.validation.ObjectError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.context.request.ServletWebRequest;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//import uyun.logmon.common.api.ApiResult;
//import uyun.logmon.common.api.Enum.ErrorCode;
//import uyun.logmon.common.api.ServiceException;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.List;
//import java.util.Objects;
//
///**
// * 全局异常处理类（非常重要的类）
// * @author oneyoung
// */
//
//@ControllerAdvice
//public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {
//    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);
//
//    private final ApiResult apiResult = new ApiResult(ErrorCode.SERVER_ERROR.getCode(),  ErrorCode.SERVER_ERROR.getMsg(), null);
//
//    @ExceptionHandler(Throwable.class)
//    @ResponseBody
//    private ResponseEntity<Object> handleControllerException(HttpServletRequest req, Throwable ex) {
//        try {
//            ServiceException e;
//            if (ex instanceof ServiceException) {
//                LOGGER.warn(" ServiceException{} ", ex.getMessage());
//                e = (ServiceException) ex;
//                ApiResult ar = new ApiResult(e.getStatusCode(), e.getMessage(), null);
//                return new ResponseEntity<>(ar, HttpStatus.BAD_REQUEST);
//            }
//            LOGGER.error("Handle controller exception", ex);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResult);
//        } catch (Throwable e) {
//            LOGGER.error("Handle controller exception failed", e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResult);
//        }
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        try {
//            String path = null;
//            if (request instanceof ServletWebRequest) {
//                path = Objects.requireNonNull(((ServletWebRequest) request).getNativeRequest(HttpServletRequest.class)).getRequestURI();
//            }
//
//            LOGGER.debug("requestUrl:{}", path, ex);
//            // 参数校验异常提示
//            if (ex instanceof MethodArgumentNotValidException) {
//                MethodArgumentNotValidException c = (MethodArgumentNotValidException) ex;
//                List<ObjectError> errors = c.getBindingResult().getAllErrors();
//                StringBuffer errorMsg = new StringBuffer();
//                if (!CollectionUtils.isEmpty(errors)) {
//                    errors.forEach(x -> errorMsg.append(x.getDefaultMessage()).append(";"));
//                }
//                LOGGER.warn("MethodArgumentNotValidException requestUrl:{} the parameter is incorrect {}", path, errorMsg.toString());
//                return ResponseEntity.status(status).body(new ApiResult(-1,errorMsg.toString()));
//            }
//            if (ex instanceof BindException) {
//                BindException c = (BindException) ex;
//                List<ObjectError> errors = c.getBindingResult().getAllErrors();
//                StringBuffer errorMsg = new StringBuffer();
//                if (!CollectionUtils.isEmpty(errors)) {
//                    errors.forEach(x -> errorMsg.append(x.getDefaultMessage()).append(";"));
//                }
//                LOGGER.warn("MethodArgumentNotValidException requestUrl:{} the parameter is incorrect {}", path, errorMsg.toString());
//                return ResponseEntity.status(status).body(new ApiResult(-1,errorMsg.toString()));
//            }
//            LOGGER.error("handleExceptionInternal exception", ex);
//            return ResponseEntity.status(status).body(apiResult);
//        } catch (Throwable e) {
//            LOGGER.error("handleExceptionInternal exception failed", ex);
//            return ResponseEntity.status(status).body(apiResult);
//        }
//    }
//
//}