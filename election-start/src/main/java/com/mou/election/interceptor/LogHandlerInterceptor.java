package com.mou.election.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Created by 沈林强(四笠) on 2021/7/3.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/3
 */
@Component
@Aspect
@Slf4j
public class LogHandlerInterceptor {

    @Pointcut("execution(public * com.mou.election.controller..*(..))")
    public void privilege() {
    }

    @Around("privilege()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        String className = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();
        String[] parameterNamesArgs = ((MethodSignature) pjp.getSignature()).getParameterNames();
        Object result = null;
        Object[] args = pjp.getArgs();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 请求的URL
        String requestURL = request.getRequestURL().toString();

        StringBuffer paramsBuf = new StringBuffer();
        // 获取请求参数集合并进行遍历拼接
        for (int i = 0; i < args.length; i++) {
            if (paramsBuf.length() > 0) {
                paramsBuf.append("|");
            }
            paramsBuf.append(parameterNamesArgs[i]).append(" = ").append(args[i]);
        }
        StringBuffer headerBuf = new StringBuffer();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            if (headerBuf.length() > 0) {
                headerBuf.append("|");
            }
            headerBuf.append(key).append("=").append(value);
        }

        // 打印请求参数参数
        long start = System.currentTimeMillis();
        log.info("请求| 请求接口:{} | 参数:{} ", requestURL, paramsBuf.toString());
        result = pjp.proceed();// 执行目标方法
        // 获取执行完的时间 打印返回报文
        log.info("返回| 请求接口:{}| 处理时间:{} 毫秒 ", requestURL, (System.currentTimeMillis() - start));
        return result;
    }


}
