package com.gdsc.goodeat.dto;

public record ReconfigureRequest(
    String originLanguageName,
    String userLanguageName,
    String originCurrencyName,
    String userCurrencyName,
    String base64EncodedImage
) {

}
