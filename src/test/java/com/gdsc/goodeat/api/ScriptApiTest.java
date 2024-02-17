package com.gdsc.goodeat.api;

import static com.gdsc.goodeat.fake.FakeTranslationClient.TRANSLATION_POST_FIX;
import static org.assertj.core.api.Assertions.assertThat;

import com.gdsc.goodeat.dto.ScriptGenerateRequest;
import com.gdsc.goodeat.dto.ScriptGenerateRequest.MenuItemRequest;
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
            new MenuItemRequest("Bún Chả", "분짜", 3)
        ),
        "Vietnamese", "Korean"
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
        "Xin chào. Tôi muốn đặt 3 Bún Chả"+TRANSLATION_POST_FIX,
        "Xin chào. Tôi muốn đặt 3 Bún Chả"
    );
    final ScriptResponse actual = response.as(ScriptResponse.class);

    assertThat(actual)
        .usingRecursiveComparison()
        .isEqualTo(expected);
  }
}
