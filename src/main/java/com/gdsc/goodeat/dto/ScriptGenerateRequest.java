package com.gdsc.goodeat.dto;

import com.gdsc.goodeat.domain.OrderItem;
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

    public OrderItem toDomain() {
      return new OrderItem(originMenuName, userMenuName, quantity);
    }
  }
}
