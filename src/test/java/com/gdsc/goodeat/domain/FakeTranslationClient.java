package com.gdsc.goodeat.domain;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class FakeTranslationClient implements TranslationClient {

  public static final String TRANSLATION_POST_FIX = "test";

  @Override
  public String translate(final Language from, final Language to, final String content) {
    return content + TRANSLATION_POST_FIX;
  }
}
