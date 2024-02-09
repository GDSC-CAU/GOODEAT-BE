package com.gdsc.goodeat.exception;

import com.gdsc.goodeat.base.BaseException;

public class LanguageException extends BaseException {

  public LanguageException(final LanguageExceptionType exceptionType) {
    super(exceptionType);
  }
}
