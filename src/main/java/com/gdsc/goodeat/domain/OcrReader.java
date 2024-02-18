package com.gdsc.goodeat.domain;

import java.util.List;

public interface OcrReader {

  /**
   * 반환하는 MenuItem의 name은 항상 영어이다. -> 크롤링에 영향을 주기 때문에
   *
   * @param base64encodedImage
   * @return
   */
  List<MenuItem> read(final String base64encodedImage);

  Language supportedLanguage();
}
