package com.gdsc.goodeat.dto;

import com.gdsc.goodeat.domain.MenuItem;
import java.util.List;

public record ScriptGenerateRequest(
    List<MenuItemRequest> menuItems,
    String originLanguageName,
    String userLanguageName
) {

  public record MenuItemRequest(
      String originMenuName,
      String userMenuName,
      int quantity
  ) {
    public MenuItem toDomain() {
      return new MenuItem(originMenuName, userMenuName, quantity);
    }
  }
}
