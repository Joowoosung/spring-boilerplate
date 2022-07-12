package com.jws.settings.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jws.domain.main.dto.LoginAuth;
import com.jws.domain.main.user.JwsUser;

import java.time.Instant;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {

  //  private static final long AUTH_TIME = 60 * 30;
  public static final long AUTH_TIME = 60 * 30 * 24 * 7;
  private static final ObjectMapper objMapper = new ObjectMapper();
  private static final Algorithm ALGORITHM = Algorithm.HMAC256("E9Y6lopBLPDEb9wA");
  private static final long REFRESH_TIME = 60 * 60 * 24 * 7;

  public static String makeAuthToken(JwsUser user) {
    return JWT.create()
        .withClaim("exp", Instant.now().getEpochSecond() + AUTH_TIME)
        .withSubject(user.getUsername())
        .withPayload(getPayload(user))
        .sign(ALGORITHM);
  }

  public static String makeRefreshToken(JwsUser user) {
    return JWT.create()
        .withClaim("exp", Instant.now().getEpochSecond() + REFRESH_TIME)
        .withSubject(user.getUsername())
        .withPayload(getPayload(user))
        .sign(ALGORITHM);
  }

  private static Map getPayload(JwsUser user) {
    Map<String, String> payload = new HashMap<>();
    payload.put("userId", String.valueOf(user.getId()));
    payload.put("username", user.getUsername());
    payload.put("role", user.getRole().getCode());
    payload.put("pageType", user.getPageType());
    return payload;
  }

  public static LoginAuth verify(String token) {
    try {
      DecodedJWT verify = JWT.require(ALGORITHM).build().verify(token);
      String decodePayloadJson = new String(Base64.getDecoder().decode(verify.getPayload()), "UTF-8");
      return new LoginAuth(objMapper.readValue(decodePayloadJson, Map.class), true);
    } catch (Exception ex) {
//        DecodedJWT decode = JWT.decode(token);
//        return LoginAuth.builder().success(false).username(decode.getSubject()).build();
      return LoginAuth.builder().success(false).build();
    }
  }

}
