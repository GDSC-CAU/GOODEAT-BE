package com.gdsc.goodeat.dto;

public record ReconfigureResponse(
    String description,
    String imageUrl,
    String originMenuName,
    String userMenuName,
    Double originPrice,
    Double userPrice
) {

}
