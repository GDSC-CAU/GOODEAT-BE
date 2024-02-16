package com.gdsc.goodeat.exception;

import com.gdsc.goodeat.base.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum FoodExceptionType implements BaseExceptionType {
  FOOD_NOT_FOUND(HttpStatus.NOT_FOUND, "요청한 음식을 찾을 수 없습니다."),
  FOOD_SCRAPING_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "tasteatlas 페이지 크롤링에 실패했습니다.");

  private final HttpStatus httpStatus;
  private final String exceptionMessage;

  FoodExceptionType(HttpStatus httpStatus, String exceptionMessage) {
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
