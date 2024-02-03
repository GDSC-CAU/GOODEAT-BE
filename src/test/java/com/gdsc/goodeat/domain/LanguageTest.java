package com.gdsc.goodeat.domain;

import static com.gdsc.goodeat.domain.Language.AFRIKAANS;
import static org.assertj.core.api.Assertions.assertThat;

import com.gdsc.goodeat.domain.Language;
import org.junit.jupiter.api.Test;

class LanguageTest {

  @Test
  void ISO639코드로_Language를_찾을_수_있다() {
    //given
    final String afIsoCode = "af";
    final Language expected = AFRIKAANS;

    //when
    final Language actual = Language.fromISOCode(afIsoCode);

    //then
    assertThat(actual)
        .isEqualTo(expected);
  }

  @Test
  void LanguageName으로_Language를_찾을_수_있다() {
    //given
    final String afLanguageName = "Afrikaans";
    final Language expected = AFRIKAANS;

    //when
    final Language actual = Language.fromLanguageName(afLanguageName);

    //then
    assertThat(actual)
        .isEqualTo(expected);
  }
}