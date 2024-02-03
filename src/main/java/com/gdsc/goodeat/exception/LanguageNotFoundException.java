package com.gdsc.goodeat.exception;

import static com.gdsc.goodeat.exception.LanguageExceptionType.LANGUAGE_NOT_FOUND;

import com.gdsc.goodeat.base.BaseException;
import com.gdsc.goodeat.base.BaseExceptionType;

public class LanguageNotFoundException extends BaseException {

  private static final LanguageExceptionType EXCEPTION_TYPE = LANGUAGE_NOT_FOUND;

  public LanguageNotFoundException() {
    super(EXCEPTION_TYPE.getExceptionMessage());
  }

  @Override
  public BaseExceptionType exceptionType() {
    return EXCEPTION_TYPE;
  }
}
