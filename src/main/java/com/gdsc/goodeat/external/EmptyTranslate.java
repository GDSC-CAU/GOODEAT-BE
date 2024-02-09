package com.gdsc.goodeat.external;

import com.google.cloud.translate.Detection;
import com.google.cloud.translate.Language;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import java.util.List;

public class EmptyTranslate implements Translate {

  @Override
  public List<Language> listSupportedLanguages(final LanguageListOption... languageListOptions) {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<Detection> detect(final List<String> list) {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<Detection> detect(final String... strings) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Detection detect(final String s) {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<Translation> translate(final List<String> list,
      final TranslateOption... translateOptions) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Translation translate(final String s, final TranslateOption... translateOptions) {
    throw new UnsupportedOperationException();
  }

  @Override
  public TranslateOptions getOptions() {
    throw new UnsupportedOperationException();
  }
}
