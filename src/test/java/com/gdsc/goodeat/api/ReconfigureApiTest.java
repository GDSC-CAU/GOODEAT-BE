package com.gdsc.goodeat.api;

import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;

import com.gdsc.goodeat.dto.ReconfigureRequest;
import com.gdsc.goodeat.dto.ReconfigureResponse;
import com.gdsc.goodeat.dto.ReconfigureResponse.CurrencyResponse;
import com.gdsc.goodeat.support.ApiTest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ReconfigureApiTest extends ApiTest {

  @Test
  void 인코딩된_이미지를_받아_번역하고_환율을_변환한_값을_반환한다() {
    //given
    final ReconfigureRequest request = new ReconfigureRequest(
        "Vietnamese", "Korean", "encodedImage"
    );

    //when
    final ExtractableResponse<Response> response = RestAssured.given()
        .contentType(JSON)
        .body(request).log().all()
        .when().post("/reconfigure")
        .then().log().all()
        .extract();

    //then
    final List<ReconfigureResponse> actual
        = Arrays.asList(response.as(ReconfigureResponse[].class));
    final List<ReconfigureResponse> expected = List.of(
        new ReconfigureResponse(
            "음식에 대한 설명", "음식 이미지URL", "", ""
            , new CurrencyResponse("Vietnamese dong", 36000)
            , new CurrencyResponse("South Korean won", 2000)
        )
    );

    assertThat(actual)
        .usingRecursiveComparison()
        .isEqualTo(expected);
  }
}