package com.gdsc.goodeat.exception;

import com.gdsc.goodeat.base.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum OcrReaderExceptionType implements BaseExceptionType {

  OCR_READER_NOT_FOUND(HttpStatus.NOT_FOUND, "요청한 언어의 ocr reader를 찾을 수 없습니다.");

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
