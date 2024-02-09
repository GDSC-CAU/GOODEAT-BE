package com.gdsc.goodeat.dto;

import com.gdsc.goodeat.domain.MenuItem;
import java.util.List;

public record ScriptGenerateRequest(
    List<MenuItemRequest> menuItems,
    String travelLanguageName,
    String userLanguageName
) {

  public record MenuItemRequest(
      String travelMenuName,
      String userMenuName,
      int quantity
  ) {
    public MenuItem toDomain() {
      return new MenuItem(travelMenuName, userMenuName, quantity);
    }
  }
}
