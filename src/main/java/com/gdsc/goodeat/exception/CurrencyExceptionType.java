package com.gdsc.goodeat.exception;

import com.gdsc.goodeat.base.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum CurrencyExceptionType implements BaseExceptionType {
  CURRENCY_NOT_FOUND(HttpStatus.NOT_FOUND, "요청한 화폐를 찾을 수 없습니다.");

  private final HttpStatus httpStatus;
  private final String exceptionMessage;

  CurrencyExceptionType(HttpStatus httpStatus, String exceptionMessage) {
    this.httpStatus = httpStatus;
    this.exceptionMessage = exceptionMessage;
  }

  @Override
  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  @Override
  public String getExceptionMessage() {
    return exceptionMessage;
  }
}
