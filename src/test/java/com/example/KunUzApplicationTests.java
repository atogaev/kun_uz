package com.example;

import com.example.util.MD5Util;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KunUzApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(MD5Util.encode("12345"));
    }

}
