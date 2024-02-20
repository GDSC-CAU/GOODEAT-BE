package com.gdsc.goodeat.dto;

import com.gdsc.goodeat.domain.Currency;

public class CurrencyResponse {

  private final String currencyName;

  private CurrencyResponse() {
    this(null);
  }

  public CurrencyResponse(final String currencyName) {
    this.currencyName = currencyName;
  }

  public static CurrencyResponse from(final Currency currency) {
    return new CurrencyResponse(currency.getCurrencyName());
  }

  public String getCurrencyName() {
    return currencyName;
  }
}
