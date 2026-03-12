package com.example.teamintroservice;

import io.awspring.cloud.s3.S3Template;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(properties = {
        "spring.config.import=optional:aws-parameterstore:",
        "spring.cloud.aws.cloudfront.domain=https://dummy.cloudfront.net"
})
@ActiveProfiles("local")
class TeamIntroServiceApplicationTests {

    @MockBean
    private S3Template s3Template;

    @Test
    void contextLoads() {
    }
}