package com.example;

import com.example.entity.ProfileEntity;
import com.example.util.JWTUtil;
import com.example.util.MD5Util;
import com.example.util.SecurityUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KunUzApplicationTests {

    @Test
    void contextLoads() {

//        System.out.println(MD5Util.encode("1"));

        String token = JWTUtil.encode(20L, ProfileEntity.Role.MODERATOR);
        System.out.println(token);
        System.out.println(JWTUtil.decode(token));
//        System.out.println(SecurityUtil.hasRole("Bearer " + token);
    }

}
