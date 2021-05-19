package com.github.oneyoung.portal.aop;

import com.github.oneyoung.common.result.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


/**
 * ControllerAspect
 *
 * @author oneyoung
 * @date 2021/4/22 11:26
 */
//@Order(0)
//@Aspect
//@Component
public class ControllerAspect {


    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);

    @Pointcut("execution(* com.github.oneyoung.portal.controller.*.*(..))")
    public void service() {
    }

    @Around("service()")
    public ResponseEntity<Result<?>> processService(ProceedingJoinPoint joinPoint) {
        return processService0(joinPoint);
    }

    protected ResponseEntity<Result<?>> processService0(ProceedingJoinPoint joinPoint) {
        printEntranceLog(joinPoint);

        long startTime = System.currentTimeMillis();
        Result<?> result = null;
        Throwable throwable = null;
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 只能处理这一类返回值类型的
        try {
            result = (Result<?>) joinPoint.proceed();
            return ResponseEntity.ok(result);
        } catch (Throwable t) {
            throwable = t;
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Result.failure(null, null));
        } finally {
            long costTime = System.currentTimeMillis() - startTime;
            // 打日志
            printResultLog(joinPoint, costTime, result, throwable);

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
