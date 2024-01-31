package com.gdsc.goodeat;

import com.gdsc.goodeat.exception.LanguageNotFoundException;
import java.util.Arrays;

public enum Language {

  AFRIKAANS("Afrikaans", "af");

  private final String languageName;
  private final String ISO639Code;

  Language(final String languageName, final String ISO639Code) {
    this.languageName = languageName;
    this.ISO639Code = ISO639Code;
  }

  public static Language fromLanguageName(final String languageName) {
    return Arrays.stream(values())
        .filter(lan -> lan.languageName.equals(languageName))
        .findAny()
        .orElseThrow(LanguageNotFoundException::new);
  }

  public static Language fromISOCode(final String code) {
    return Arrays.stream(values())
        .filter(lan -> lan.ISO639Code.equals(code))
        .findAny()
        .orElseThrow(LanguageNotFoundException::new);
  }
}
