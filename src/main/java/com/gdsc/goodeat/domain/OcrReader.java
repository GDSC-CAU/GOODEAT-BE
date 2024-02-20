package com.gdsc.goodeat.domain;

import java.util.List;

public interface OcrReader {

  List<MenuItem> read(final String base64encodedImage);
}
