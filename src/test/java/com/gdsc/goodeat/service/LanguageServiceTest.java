package com.gdsc.goodeat.service;

import static com.gdsc.goodeat.domain.Language.AFRIKAANS;
import static org.assertj.core.api.Assertions.assertThat;

import com.gdsc.goodeat.dto.LanguageResponse;
import com.gdsc.goodeat.support.ServiceTest;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class LanguageServiceTest extends ServiceTest {

  @Autowired
  private LanguageService languageService;

  @Test
  void 언어_목록을_조회할_수_있다(){
    //given, when
    final LanguageResponse expectedElement = LanguageResponse.from(AFRIKAANS);
    final List<LanguageResponse> actual = languageService.findAllLanguages();

    //then
    assertThat(actual)
        .usingRecursiveFieldByFieldElementComparator()
        .contains(expectedElement);
  }
}