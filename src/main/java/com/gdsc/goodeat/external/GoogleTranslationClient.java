package com.gdsc.goodeat.external;

import com.gdsc.goodeat.domain.Language;
import com.gdsc.goodeat.domain.TranslationClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GoogleTranslationClient implements TranslationClient {

  public final String apiKey;

  public GoogleTranslationClient(@Value("${apikey.google}") final String apiKey) {
    this.apiKey = apiKey;
  }

  public String translate(final Language from, final Language to, final String content) {
    return null;
  }
}
