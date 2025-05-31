package io.github.trident.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.github.trident.common.model.UserInfo;
import org.apache.dubbo.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Optional;
import org.apache.shiro.SecurityUtils;
/**
 * @projectName: trident
 * @package: io.github.trident.common.utils
 * @className: JwtUtils
 * @author: frank.wu
 * @description: TODO
 * @date: 2025/3/2 11:26
 * @version: 1.0
 */
public final class JwtUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);
    private static final long TOKEN_EXPIRE_SECONDS = 24 * 60 * 60 * 1000L;

    private JwtUtils() {
    }

    public static UserInfo getUserInfo() {
        return (UserInfo) SecurityUtils.getSubject().getPrincipal();
    }

    public static String generateToken(final String userName, final String key, final String clientId) {
        return generateToken(userName, key, clientId, null);
    }

    public static String generateToken(final String userName, final String key, final String clientId, final Long expireSeconds) {
        try {
            return JWT.create()
                    .withClaim("userName", userName)
                    .withClaim("clientId", clientId)
                    .withExpiresAt(new Date(System.currentTimeMillis() + Optional.ofNullable(expireSeconds).orElse(TOKEN_EXPIRE_SECONDS)))
                    .sign(Algorithm.HMAC256(key));
        } catch (IllegalArgumentException | JWTCreationException e) {
            LOGGER.error("JWTToken generate fail ", e);
        }
        return StringUtils.EMPTY_STRING;
    }

    public static boolean verifyToken(final String token, final String key) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(key)).build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            LOGGER.error("jwt decode fail, token: {} ", token, e);
        }
        return false;
    }

    public static String getIssuer(final String token) {
        DecodedJWT jwt = JWT.decode(token);
        return Optional.of(jwt).map(item -> item.getClaim("userName").asString()).orElse("");
    }

    public static String getClientId(final String token) {
        DecodedJWT jwt = JWT.decode(token);
        return Optional.of(jwt).map(item -> item.getClaim("clientId").asString()).orElse("");
    }

    public static void main(String[] args) {
        String KEY = "ba3253876aed6bc22d4a6ff53d8406c6ad864195ed144ab5c87621b6c233b548baeae6956df346ec8c17f5ea10f35ee3cbc514797ed7ddd3145464e2a0bab413";
        String token = generateToken("admin", KEY, "8000", 2592000000L);
        System.out.println(token);
        System.out.println(verifyToken(token, KEY));
        System.out.println(getIssuer(token));
    }
}
