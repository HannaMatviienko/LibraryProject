package com.library;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class LibrarySpringApplicationTests {

    @Test
    void contextLoads() {
        BCryptPasswordEncoder en = new BCryptPasswordEncoder();
        System.out.println(en.encode("pass"));
    }

}
