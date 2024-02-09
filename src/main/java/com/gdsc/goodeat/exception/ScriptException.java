package com.gdsc.goodeat.exception;

import com.gdsc.goodeat.base.BaseException;

public class ScriptException extends BaseException {

  public ScriptException(final ScriptExceptionType exceptionType) {
    super(exceptionType);
  }
}
