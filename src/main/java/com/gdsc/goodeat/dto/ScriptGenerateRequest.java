package com.gdsc.goodeat.dto;

import java.util.List;

public record ScriptGenerateRequest(
    List<OrderRequest> orders,
    String travelLanguageName,
    String userLanguageName
) {

  public record OrderRequest(
      String travelMenuName,
      String userMenuName,
      int count
  ) {

  }
}
