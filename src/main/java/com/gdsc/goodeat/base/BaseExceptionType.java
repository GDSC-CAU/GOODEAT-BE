package com.gdsc.goodeat.base;

import org.springframework.http.HttpStatus;

public interface BaseExceptionType {

  HttpStatus getHttpStatus();

  String getExceptionMessage();
}
