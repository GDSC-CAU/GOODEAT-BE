package com.gdsc.goodeat.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import com.gdsc.goodeat.base.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum OcrReaderExceptionType implements BaseExceptionType {

  OCR_READ_FAIL(INTERNAL_SERVER_ERROR, "OCR을 읽는데 실패했습니다."),
  OCR_RESULT_IS_NOT_JSON(INTERNAL_SERVER_ERROR, "OCR 결과를 객체로 변환하는데 실패했습니다.");

  private final HttpStatus httpStatus;
  private final String exceptionMessage;

  OcrReaderExceptionType(final HttpStatus httpStatus, final String exceptionMessage) {
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
