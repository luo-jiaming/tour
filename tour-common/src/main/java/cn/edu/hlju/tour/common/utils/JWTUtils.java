package cn.edu.hlju.tour.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;


/**
 * Created by Sole on 2017/3/1.
 */
public class JWTUtils {

    private static final Logger logger = LoggerFactory.getLogger(JWTUtils.class);

    private static final String SECRET = "secret";

    public static <T> String sign(T object, Long expiry, String issuer, String audience) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(object);
            Date expiryDate = new Date(new Date().getTime() + expiry);
            String token = JWT.create()
                    .withIssuer(issuer)
                    .withAudience(audience)
                    .withClaim("instance", jsonString)
                    .withExpiresAt(expiryDate)
                    .sign(Algorithm.HMAC256(SECRET));
            return token;
        } catch (Exception e) {
            logger.info("##########jwt创建token出现异常" + e);
        }
        return null;
    }


    /**
     * 获取 token 里面的内容
     * @param token
     * @param classT
     * @param <T>
     * @return
     */
    public static <T> T unsign(String token, Class<T> classT) {
        try {
            JWT jwt = JWT.decode(token);
            String json = jwt.getClaim("instance").asString();
            ObjectMapper mapper = new ObjectMapper();
            T t = mapper.readValue(json, classT);
            return t;
        } catch (Exception e) {
            logger.info("##########token验证失败" + e);
        }
        return null;
    }


    /**
     * 验证 token
     * @param token
     * @return
     */
    public static boolean validate(String token, String issuer, String audience) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET))
                    .build();
            JWT jwt = (JWT) verifier.verify(token);
            if (!jwt.getIssuer().equals(issuer)) {
                return false;
            }
            if (!jwt.getAudience().equals(audience)) {
                return false;
            }
            if (jwt.getExpiresAt().getTime() < new Date().getTime()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            logger.info("##########token验证失败" + e);
            return false;
        }
    }


}
