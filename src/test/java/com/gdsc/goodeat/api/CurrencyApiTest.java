package com.gdsc.goodeat.api;

import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;

import com.gdsc.goodeat.dto.CalculatePriceRequest;
import com.gdsc.goodeat.dto.CalculatePriceRequest.OrderElementRequest;
import com.gdsc.goodeat.dto.CalculatePriceResponse;
import com.gdsc.goodeat.support.ApiTest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.Test;

public class CurrencyApiTest extends ApiTest {

  @Test
  void 화폐단위와_가격_수량을_주면_총가격을_반환해준다() {
    final CalculatePriceRequest request = new CalculatePriceRequest(
        "South Korean won", "Vietnamese dong",
        List.of(
            new OrderElementRequest(2000.0, 10000.0, 2),
            new OrderElementRequest(3000.0, 20000.0, 1)
        )
    );

    final ExtractableResponse<Response> response = RestAssured.given().log().all()
        .contentType(JSON)
        .body(request).log().all()
        .when().post("/currency/calculate")
        .then().log().all()
        .extract();

    final CalculatePriceResponse expected = new CalculatePriceResponse(
        "₩ 7,000", "₫ 40,000"
    );

    final CalculatePriceResponse actual = response.as(CalculatePriceResponse.class);

    assertThat(actual)
        .usingRecursiveComparison()
        .isEqualTo(expected);
  }
}
