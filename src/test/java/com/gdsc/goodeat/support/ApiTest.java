package com.gdsc.goodeat.support;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@SuppressWarnings("NonAsciiCharacters")
public class ApiTest {

  @LocalServerPort
  private int port;

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
  }
}
