package com.gdsc.goodeat.exception;

import static com.gdsc.goodeat.exception.CurrencyExceptionType.CURRENCY_NOT_FOUND;

import com.gdsc.goodeat.base.BaseException;
import com.gdsc.goodeat.base.BaseExceptionType;

public class CurrencyNotFoundException extends BaseException {

  private static final CurrencyExceptionType EXCEPTION_TYPE = CURRENCY_NOT_FOUND;

  public CurrencyNotFoundException() {
    super(EXCEPTION_TYPE.getExceptionMessage());
  }

  @Override
  public BaseExceptionType exceptionType() {
    return EXCEPTION_TYPE;
  }
}
