package com.wyu.video.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.wyu.video.enums.ResultEnum;
import com.wyu.video.exception.ConditionException;

import java.sql.PreparedStatement;
import java.util.Calendar;
import java.util.Date;

/**
 * @author zwx
 * @date 2022-06-19 13:02
 */
public class TokenUtil {

    // TODO token过期时间，签名 硬编码
    private static final String ISSUER = "WYU";

    public static String generateToken(Long userId) throws Exception {
        Algorithm algorithm = Algorithm.RSA256(RSAUtil.getPublicKey(), RSAUtil.getPrivateKey());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.SECOND, 60);

        return JWT.create()
                .withKeyId(String.valueOf(userId))
                .withIssuer(ISSUER)
                .withExpiresAt(calendar.getTime())
                .sign(algorithm);
    }

    public static Long verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.RSA256(RSAUtil.getPublicKey(), RSAUtil.getPrivateKey());
            JWTVerifier verifier = JWT.require(algorithm).build(); // JWT验证类
            DecodedJWT jwt = verifier.verify(token);
            String userId = jwt.getKeyId();
            return Long.valueOf(userId);
        } catch (TokenExpiredException e) {
            throw new ConditionException(ResultEnum.TOKEN_EXPIRED.getCode(), ResultEnum.TOKEN_EXPIRED.getDesc());
        } catch (Exception e) {
            throw new ConditionException(ResultEnum.TOKEN_ERROR.getCode(), ResultEnum.TOKEN_ERROR.getDesc());
        }
    }
}
