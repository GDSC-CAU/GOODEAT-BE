package com.gdsc.goodeat.dto;

public record ReconfigureRequest(
    String travelLanguageName,
    String userLanguageName,
    String base64EncodedImage
) {


}
