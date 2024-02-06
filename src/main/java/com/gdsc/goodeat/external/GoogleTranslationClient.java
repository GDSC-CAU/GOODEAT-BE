package com.gdsc.goodeat.external;

import com.gdsc.goodeat.domain.Language;
import com.gdsc.goodeat.domain.TranslationClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class GoogleTranslationClient implements TranslationClient {

  public final String apiKey;

  public GoogleTranslationClient(@Value("${apikey.google}: not prod") final String apiKey) {
    this.apiKey = apiKey;
  }

  public String translate(final Language from, final Language to, final String content) {
    return null;
  }
}
