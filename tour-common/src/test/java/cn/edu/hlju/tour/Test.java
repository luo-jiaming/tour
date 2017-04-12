package cn.edu.hlju.tour;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.UUID;

/**
 * Created by Sole on 2017/3/3.
 */
public class Test {

    @org.junit.Test
    public void test() {
        System.out.println(UUID.randomUUID().toString());
    }


    @org.junit.Test
    public void testJwt() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(new User("wenweijie", 20));
            String token = JWT.create()
                    .withClaim("instance", jsonString).toString();
            System.out.println(token);
        } catch (Exception e) {
        }
    }

}
