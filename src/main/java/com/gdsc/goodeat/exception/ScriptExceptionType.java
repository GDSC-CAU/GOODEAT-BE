package com.gdsc.goodeat.exception;

import com.gdsc.goodeat.base.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum ScriptExceptionType implements BaseExceptionType {

  SCRIPT_GENERATOR_NOT_FOUND(HttpStatus.NOT_FOUND, "요청한 언어로 스크립트를 생성할 수 없습니다. 번역가능한 언어를 골라주세요");

  private final HttpStatus httpStatus;
  private final String exceptionMessage;

  ScriptExceptionType(final HttpStatus httpStatus, final String exceptionMessage) {
    this.httpStatus = httpStatus;
    this.exceptionMessage = exceptionMessage;
  }

  @Override
  public HttpStatus getHttpStatus() {
    return null;
  }

  @Override
  public String getExceptionMessage() {
    return null;
  }
}
