package com.gdsc.goodeat.exception;

import com.gdsc.goodeat.base.BaseException;

public class CurrencyException extends BaseException {

  public CurrencyException(final CurrencyExceptionType currencyExceptionType) {
    super(currencyExceptionType);
  }
}
