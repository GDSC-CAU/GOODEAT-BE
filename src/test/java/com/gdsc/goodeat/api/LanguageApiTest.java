package com.gdsc.goodeat.api;

import static com.gdsc.goodeat.domain.Language.AFRIKAANS;
import static org.assertj.core.api.Assertions.assertThat;

import com.gdsc.goodeat.domain.Language;
import com.gdsc.goodeat.dto.LanguageResponse;
import com.gdsc.goodeat.support.ApiTest;
import com.jayway.jsonpath.TypeRef;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class LanguageApiTest extends ApiTest {

  @Test
  void 언어_목록을_조회할_수_있다(){
    //given, when
    final ExtractableResponse<Response> response = RestAssured.given().log().all()
        .when().get("/lagnuages")
        .then().log().all()
        .extract();

    final List<LanguageResponse> expected = Arrays.stream(Language.values())
        .map(LanguageResponse::from)
        .toList();

    final List<LanguageResponse> actual = Arrays.asList(response.as(LanguageResponse[].class));

    assertThat(actual)
        .usingRecursiveFieldByFieldElementComparator()
        .containsExactlyInAnyOrderElementsOf(expected);
  }
}
