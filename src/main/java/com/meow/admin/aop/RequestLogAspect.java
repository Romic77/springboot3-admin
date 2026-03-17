package com.meow.admin.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Slf4j
@Aspect
@Component
public class RequestLogAspect {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
            .withZone(ZoneId.systemDefault());

    /**
     * 拦截 com.meow.admin.controller 包及其子包中所有 public 方法
     */
    @Pointcut("execution(public * com.meow.admin.controller..*.*(..))")
    public void adminControllerMethods() {
    }

    @Around("adminControllerMethods()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes)
                Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        String url = request.getRequestURI();
        String methodType = request.getMethod();
        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();
        long duration = end - start;

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String method = signature.getDeclaringType().getSimpleName() + "." + signature.getName();

        String startStr = FORMATTER.format(Instant.ofEpochMilli(start));
        String endStr = FORMATTER.format(Instant.ofEpochMilli(end));

        log.info("[接口访问] 请求方式:{} URL: {} 方法: {} 开始时间: {} 结束时间: {} 耗时: {} ms", methodType, url, method, startStr, endStr, duration);

        return result;
    }
}
