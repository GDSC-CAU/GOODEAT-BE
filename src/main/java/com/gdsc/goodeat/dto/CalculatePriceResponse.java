package com.gdsc.goodeat.dto;

public record CalculatePriceResponse(
    String totalPriceWithOriginCurrencyUnit,
    String totalPriceWithUserCurrencyUnit
) {

}
