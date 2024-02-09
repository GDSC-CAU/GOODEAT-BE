package com.gdsc.goodeat.base;

public abstract class BaseException extends RuntimeException {

  private final BaseExceptionType baseExceptionType;

  protected BaseException(final BaseExceptionType exceptionType) {
    super(exceptionType.getExceptionMessage());
    baseExceptionType = exceptionType;
  }

  public BaseExceptionType exceptionType(){
    return baseExceptionType;
  }
}
