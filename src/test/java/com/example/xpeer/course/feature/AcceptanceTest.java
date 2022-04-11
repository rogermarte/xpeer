package com.example.xpeer.course.feature;

import com.example.xpeer.XpeerApplication;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = XpeerApplication.class
)
@ExtendWith(SpringExtension.class)
public abstract class AcceptanceTest {

    @LocalServerPort
    private int serverPort = 0;

    @BeforeEach
    public void setUp() {
        RestAssured.port = serverPort;
        RestAssured.defaultParser = Parser.JSON;
    }

}
