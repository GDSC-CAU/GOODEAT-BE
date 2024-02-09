package com.gdsc.goodeat.api;

import static org.assertj.core.api.Assertions.assertThat;

import com.gdsc.goodeat.dto.ScriptGenerateRequest;
import com.gdsc.goodeat.dto.ScriptGenerateRequest.OrderRequest;
import com.gdsc.goodeat.dto.ScriptResponse;
import com.gdsc.goodeat.support.ApiTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ScriptApiTest extends ApiTest {

  @Test
  void 스크립트를_생성할_수_있다() {
    //given
    final ScriptGenerateRequest request = new ScriptGenerateRequest(
        List.of(
            new OrderRequest("Bún Chả", "분짜", 3)
        ),
        "ki", "vi"
    );

    //when
    final ExtractableResponse<Response> response = RestAssured.given()
        .contentType(ContentType.JSON)
        .body(request).log().all()
        .when().post("/script")
        .then().log().all()
        .extract();

    //then
    final ScriptResponse expected = new ScriptResponse(
        "안녕하세요. 분짜 3개를 주문하겠습니다.",
        "Xin chào. Tôi sẽ gọi 3 Bún Chả."
    );
    final ScriptResponse actual = response.as(ScriptResponse.class);

    assertThat(actual)
        .usingRecursiveAssertion()
        .isEqualTo(expected);
  }
}
