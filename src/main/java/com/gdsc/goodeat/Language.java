package com.gdsc.goodeat;

import java.util.Arrays;

public enum Language {

  AFRIKAANS("Afrikaans", "af");

  private final String languageName;
  private final String ISO639Code;

  Language(final String languageName, final String ISO639Code) {
    this.languageName = languageName;
    this.ISO639Code = ISO639Code;
  }
}
