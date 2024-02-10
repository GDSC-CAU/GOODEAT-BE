package com.gdsc.goodeat.dto;

public record ReconfigureResponse(
    String description,
    String imageUrl,
    String travelMenuName,
    String userMenuName,
    CurrencyResponse travelUnit,
    CurrencyResponse userUnit
) {

  public record CurrencyResponse(
      String currencyName,
      int amount
  ) {

  }
}
