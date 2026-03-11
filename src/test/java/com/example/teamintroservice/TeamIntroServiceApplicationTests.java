package com.example.teamintroservice;

import io.awspring.cloud.s3.S3Template;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(properties = {
        "spring.config.import=optional:aws-parameterstore:",
        "cloudfront-domain=https://dummy.cloudfront.net"
})
class TeamIntroServiceApplicationTests {

    @MockBean
    private S3Template s3Template;

    @Test
    void contextLoads() {
    }
}