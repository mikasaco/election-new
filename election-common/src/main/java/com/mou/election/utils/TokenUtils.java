package com.mou.election.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mou.election.enums.ErrorCodeEnum;
import com.mou.election.exception.EbizException;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 沈林强(四笠) on 2021/7/3.
 *
 * @author 沈林强(四笠)
 * @date 2021/7/3
 */
public class TokenUtils {

    public static final String SECRET = "2384jd8fjie2fvr";

    public static final int calendarField = Calendar.MINUTE;

    public static final int calendarInterval = 10;

    public static String getToken(Long userId) {
        Date iatDate = new Date();
        // expire time
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(calendarField, calendarInterval);
        Date expiresDate = nowTime.getTime();

        // header Map
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        // build token
        // param backups {iss:Service, aud:APP}
        String token = JWT.create().withHeader(map) // header
                .withClaim("iss", "Service") // payload
                .withClaim("aud", "APP").withClaim("user_id", null == userId ? null : userId.toString())
                .withIssuedAt(iatDate) // sign time
                .withExpiresAt(expiresDate) // expire time
                .sign(Algorithm.HMAC256(SECRET)); // signature

        return token;
    }

    public static Long verify(String token){
        try {
            JWTVerifier jwtVerifier=JWT.require(Algorithm.HMAC256(SECRET)).withIssuer("Service").build();//创建token验证器
            DecodedJWT decodedJWT=jwtVerifier.verify(token);
            return decodedJWT.getClaim("userId").asLong();

        } catch (IllegalArgumentException | JWTVerificationException e) {
            throw new EbizException(ErrorCodeEnum.PARAM_ERROR);
        }
    }
}
