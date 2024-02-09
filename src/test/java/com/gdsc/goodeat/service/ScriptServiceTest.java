package com.gdsc.goodeat.service;

import static com.gdsc.goodeat.domain.FakeTranslationClient.TRANSLATION_POST_FIX;
import static org.assertj.core.api.Assertions.assertThat;

import com.gdsc.goodeat.dto.ScriptGenerateRequest;
import com.gdsc.goodeat.dto.ScriptGenerateRequest.MenuItemRequest;
import com.gdsc.goodeat.dto.ScriptResponse;
import com.gdsc.goodeat.support.ServiceTest;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ScriptServiceTest extends ServiceTest {

  @Autowired
  private ScriptService scriptService;

  @Test
  void 스크립트를_정상적으로_생성한다() {
    //given
    final ScriptGenerateRequest request = new ScriptGenerateRequest(
        List.of(
            new MenuItemRequest("Bún Chả", "분짜", 3)
        ),
        "Vietnamese", "Korean"
    );

    //when
    final ScriptResponse actual = scriptService.generateScript(request);
    final ScriptResponse expected = new ScriptResponse(
        "Xin chào. Tôi muốn đặt 3 Bún Chả" + TRANSLATION_POST_FIX,
        "Xin chào. Tôi muốn đặt 3 Bún Chả"
    );

    assertThat(actual)
        .usingRecursiveComparison()
        .isEqualTo(expected);
  }
}