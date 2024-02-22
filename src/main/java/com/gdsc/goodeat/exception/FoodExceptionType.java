package com.gdsc.goodeat.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.gdsc.goodeat.base.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum FoodExceptionType implements BaseExceptionType {

  FOOD_NOT_FOUND(NOT_FOUND, "요청한 음식을 찾을 수 없습니다."),
  TASTE_ATLAS_FOOD_SCRAPING_FAILED(INTERNAL_SERVER_ERROR, "tasteatlas 페이지 크롤링에 실패했습니다."),
  GEMINI_FOOD_SCRAPING_FAILED(INTERNAL_SERVER_ERROR, "Gemini로 음식 정보를 크롤링하는데 실패했습니다"),
  FOOD_RESULT_IS_NOT_JSON(INTERNAL_SERVER_ERROR, "음식정보가 객체로 변환되는데 실패했습니다.");

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
