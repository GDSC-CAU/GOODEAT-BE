package com.gdsc.goodeat.domain;

import static com.gdsc.goodeat.domain.Language.VIETNAMESE;
import static com.gdsc.goodeat.exception.ScriptExceptionType.SCRIPT_GENERATOR_NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.gdsc.goodeat.exception.ScriptException;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ScriptGeneratorCompositeTest {

  @Autowired
  private ScriptGeneratorComposite scriptGeneratorComposite;

  @Test
  void 언어와_스크립트를_입력하면_스크립트를_생성해준다() {
    //given
    final List<OrderItem> orderItems = List.of(new OrderItem("Bún Chả", "분짜", 3));

    //when
    final Script expected = new Script("Xin chào. Tôi muốn đặt 3 Bún Chả");
    final Script actual = scriptGeneratorComposite.genrateScript(VIETNAMESE, orderItems);

    //then
    assertThat(actual)
        .usingRecursiveComparison()
        .isEqualTo(expected);
  }

  @Test
  void 지원되는_스크립트_생성기가_없으면_예외를_반환한다() {
    final Language unsupportedLanguage = Language.AFRIKAANS;

    assertThatThrownBy(
        () -> scriptGeneratorComposite.genrateScript(unsupportedLanguage, Collections.emptyList())
    )
        .isInstanceOf(ScriptException.class)
        .hasMessage(SCRIPT_GENERATOR_NOT_FOUND.getExceptionMessage());

  }
}