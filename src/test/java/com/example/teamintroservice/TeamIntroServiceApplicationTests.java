package com.example.teamintroservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.config.import=optional:aws-parameterstore:")
class TeamIntroServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
