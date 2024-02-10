package com.gdsc.goodeat.exception;

import com.gdsc.goodeat.base.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum CurrencyExceptionType implements BaseExceptionType {
  CURRENCY_NOT_FOUND(HttpStatus.NOT_FOUND, "요청한 화폐를 찾을 수 없습니다."),
  CURRENCY_RATE_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "현재 환율 정보를 불러올 수 없습니다."),
  CURRENCY_RATE_CRAWLING_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "Google Finance 페이지 크롤링에 실패했습니다."),
  CURRENCY_RATE_ELEMENT_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "Google Finance 페이지에서 환율 요소를 찾을 수 없습니다.");

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
