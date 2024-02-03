package com.gdsc.goodeat;

import com.gdsc.goodeat.base.BaseException;
import com.gdsc.goodeat.base.BaseExceptionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(BaseException.class)
  public ResponseEntity<String> handleException(final BaseException exception) {
    final BaseExceptionType type = exception.exceptionType();

    if (type.getHttpStatus().is5xxServerError()) {
      log.error("[ERROR] MESSAGE : {}, 로그 캡처와 함께 서버 개발자에게 연락주세요 : ",
          type.getExceptionMessage(), exception);
    } else if (type.getHttpStatus().is4xxClientError()) {
      log.warn("[WARN] MESSAGE: {}", type.getExceptionMessage());
      log.debug("stackTrace : ", exception);
    }

    return ResponseEntity.status(type.getHttpStatus())
        .body(type.getExceptionMessage());
  }
}
