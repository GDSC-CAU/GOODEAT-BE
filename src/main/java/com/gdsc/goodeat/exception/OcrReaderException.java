package com.gdsc.goodeat.exception;

import com.gdsc.goodeat.base.BaseException;
import com.gdsc.goodeat.base.BaseExceptionType;

public class OcrReaderException extends BaseException {

  public OcrReaderException(final OcrReaderExceptionType exceptionType) {
    super(exceptionType);
  }
}
