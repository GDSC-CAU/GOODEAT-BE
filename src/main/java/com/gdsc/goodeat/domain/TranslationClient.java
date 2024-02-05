package com.gdsc.goodeat.domain;

public interface TranslationClient {

  String translate(final Language from, final Language to, final String content);
}
