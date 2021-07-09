package com.mou.election.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.mou.election.EUserManager;
import com.mou.election.annotation.PassToken;
import com.mou.election.enums.ErrorCodeEnum;
import com.mou.election.exception.EbizException;
import com.mou.election.model.EUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created by 沈林强(四笠) on 2021/7/3.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/3
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    EUserManager euserManager;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("token");
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        // 暂时默认除了 PassToken 的其他都需要校验
        //检查有没有需要用户权限的注解
//        if (method.isAnnotationPresent(UserLoginToken.class)) {
//            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
//            if (userLoginToken.required()) {
        if (token == null) {
            throw new EbizException(ErrorCodeEnum.TOKEN_NOT_EXIST);
        }
        String userId;
        try {
            userId = JWT.decode(token).getClaim("user_id").asString();
        } catch (JWTDecodeException j) {
            throw new RuntimeException("401");
        }
        EUserDTO euserDTO = euserManager.getUserById(Long.valueOf(userId));
        if (euserDTO == null) {
            throw new RuntimeException("用户不存在，请重新登录");
        }
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("2384jd8fjie2fvr")).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new EbizException(ErrorCodeEnum.TOKEN_NOT_EXIST);
        }
        return true;
//            }
//        }
//        return true;
    }


}