package com.gdsc.goodeat.exception;

import com.gdsc.goodeat.base.BaseException;

public class FoodException extends BaseException {

  public FoodException(final FoodExceptionType foodExceptionType) {
    super(foodExceptionType);
  }
}