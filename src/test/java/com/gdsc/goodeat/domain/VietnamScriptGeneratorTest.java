package com.gdsc.goodeat.domain;

import static com.gdsc.goodeat.domain.Language.VIETNAMESE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class VietnamScriptGeneratorTest {

  private final VietnamScriptGenerator vietnamScriptGenerator = new VietnamScriptGenerator();

  @Test
  void 지원하는_언어를_반환할_수_있다() {
    final Language language = vietnamScriptGenerator.supportedLanguage();

    assertThat(language)
        .isEqualTo(VIETNAMESE);
  }

  @Test
  void 메뉴_아이템을_받으면_스크립트를_반환한다() {
    //given
    final List<MenuItem> menuItems = List.of(
        new MenuItem("Bún Chả", "분짜", 3),
        new MenuItem("Pho", "쌀국수", 2)
    );

    //when
    final Script expected = new Script("Xin chào. Tôi muốn đặt 3 Bún Chả và 2 Pho");
    final Script actual = vietnamScriptGenerator.generate(menuItems);

    assertThat(actual)
        .usingRecursiveAssertion()
        .isEqualTo(expected);
  }
}