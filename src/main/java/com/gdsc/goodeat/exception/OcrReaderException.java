package com.gdsc.goodeat.exception;

import com.gdsc.goodeat.base.BaseException;

public class OcrReaderException extends BaseException {

  public OcrReaderException(final OcrReaderExceptionType exceptionType) {
    super(exceptionType);
  }
}
