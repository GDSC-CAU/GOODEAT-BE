package com.gdsc.goodeat.external;

import com.gdsc.goodeat.domain.Language;
import com.gdsc.goodeat.domain.TranslationClient;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class GoogleTranslationClient implements TranslationClient {

  public final Translate translate;

  public GoogleTranslationClient() {
    translate = TranslateOptions.getDefaultInstance().getService();
  }

  public String translate(final Language source, final Language target, final String content) {
    final Translation translation = translate.translate(
        content
        , TranslateOption.sourceLanguage(source.getISO639Code())
        , TranslateOption.targetLanguage(target.getISO639Code())
    );
    return translation.getTranslatedText();
  }
}
