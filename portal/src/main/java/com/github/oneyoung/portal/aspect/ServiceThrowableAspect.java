package com.github.oneyoung.portal.aspect;


import com.oneyoung.common.message.ErrorCodeException;
import com.oneyoung.common.result.Result;
import com.oneyoung.common.result.Results;
import org.apache.commons.lang3.RandomStringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolationException;

/**
 * 处理所有出去的Service的异常结果，转换为Result.fail
 *
 * @author oneyoung
 * @since 2020/12/13 16:11
 */
@Aspect
@Component
@Order()
public class ServiceThrowableAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceThrowableAspect.class);

    @Pointcut("execution(* com.github.oneyoung.portal.controller.*.*(..))")
    public void service() {
    }

    @Around("service()")
    public Result<?> processService(ProceedingJoinPoint joinPoint) {
        return processService0(joinPoint);
    }

    protected Result<?> processService0(ProceedingJoinPoint joinPoint) {
        appendMDC();
        printEntranceLog(joinPoint);

        long startTime = System.currentTimeMillis();
        Result<?> result = null;
        Throwable throwable = null;
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 只能处理这一类返回值类型的
        try {
            result = (Result<?>) joinPoint.proceed();
            return result;
        }catch (ErrorCodeException e){
            result = e.toResult();
            return result;
        } catch (IllegalArgumentException | UnsupportedOperationException t) {
            throwable = t;
            result = Results.failIllegalArgument(t.getMessage());
            return result;
        } catch (ConstraintViolationException t) {
            // 这个不需要打印堆栈
            result = Results.failValidation(t);
            return result;
        } catch (Throwable t) {
            throwable = t;
            result = Results.failException(t);
            return result;
        } finally {
            long costTime = System.currentTimeMillis() - startTime;
            // 打日志
            printResultLog(joinPoint, costTime, result, throwable);
            clearMDC();
        }
    }

    private void appendMDC() {
        try {
            MDC.put("TRACE_ID", RandomStringUtils.randomAlphanumeric(16));
            MDC.put("TENANT_ID", RandomStringUtils.randomAlphanumeric(16));
        } catch (Throwable t) {
        }
    }

    private void clearMDC() {
        try {
            MDC.remove("TRACE_ID");
            MDC.remove("TENANT_ID");
        } catch (Throwable t) {
        }
    }

    /**
     * 打印入口日志
     */
    private void printEntranceLog(ProceedingJoinPoint joinPoint) {
        try {
            Signature signature = joinPoint.getSignature();
            LOGGER.info("EntranceLog class {}, method {}, args {}", signature.getDeclaringTypeName(), signature.getName(), joinPoint.getArgs());
        } catch (Throwable t) {
            LOGGER.error("打印入口日志异常", t);
        }
    }

    /**
     * 打印结果日志
     */
    private void printResultLog(ProceedingJoinPoint joinPoint, long costTime, Object result, Throwable throwable) {
        try {
            Signature signature = joinPoint.getSignature();
            if (throwable != null) {
                LOGGER.error("ResultLog {}#{} costTime {}, result={}", signature.getDeclaringTypeName(), signature.getName(), costTime, result, throwable);
            } else {
                LOGGER.info("ResultLog {}#{} costTime {}, result={}", signature.getDeclaringTypeName(), signature.getName(), costTime, result);
            }
        } catch (Throwable t) {
            LOGGER.error("打印结果日志异常", t);
        }
    }
}
