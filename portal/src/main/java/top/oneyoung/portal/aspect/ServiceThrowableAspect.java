package top.oneyoung.portal.aspect;


import top.oneyoung.common.message.ErrorCodeException;
import top.oneyoung.common.result.Result;
import top.oneyoung.common.result.Results;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@SuppressWarnings("unchecked")
public class ServiceThrowableAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceThrowableAspect.class);

    @Pointcut("execution(* top.oneyoung.portal.controller.*.*(..))")
    public void service() {
        // do nothing
    }

    @Around("service()")
    public Result<Object> processService(ProceedingJoinPoint joinPoint) {
        return processService0(joinPoint);
    }

    protected Result<Object> processService0(ProceedingJoinPoint joinPoint) {
        printEntranceLog(joinPoint);

        long startTime = System.currentTimeMillis();
        Result<Object> result = null;
        Throwable throwable = null;
        // 只能处理这一类返回值类型的
        try {
            result = (Result<Object>) joinPoint.proceed();
            return result;
        } catch (ErrorCodeException e) {
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
        }
    }


    /**
     * 打印入口日志
     */
    private void printEntranceLog(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        LOGGER.info("EntranceLog class {}, method {}, args {}", signature.getDeclaringTypeName(), signature.getName(), joinPoint.getArgs());
    }

    /**
     * 打印结果日志
     */
    private void printResultLog(ProceedingJoinPoint joinPoint, long costTime, Object result, Throwable throwable) {
        Signature signature = joinPoint.getSignature();
        if (throwable != null) {
            LOGGER.error("ResultLog {}#{} costTime {}, result={}", signature.getDeclaringTypeName(), signature.getName(), costTime, result, throwable);
        } else {
            LOGGER.info("ResultLog {}#{} costTime {}, result={}", signature.getDeclaringTypeName(), signature.getName(), costTime, result);
        }
    }
}
