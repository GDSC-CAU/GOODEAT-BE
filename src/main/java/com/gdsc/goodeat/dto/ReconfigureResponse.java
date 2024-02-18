package com.gdsc.goodeat.dto;

import com.gdsc.goodeat.domain.FoodScraper.FoodInfo;
import com.gdsc.goodeat.domain.MenuItem;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class ReconfigureResponse {

  private final String description;
  private final String imageUrl;
  private final String originMenuName;
  private final String userMenuName;
  private final Double originPrice;
  private final Double userPrice;

  public static ReconfigureResponse createResponse(
      final FoodInfo foodInfo, final MenuItem menuItem,
      final String userMenuName, final Double userPrice
  ) {
    return new ReconfigureResponse(
        foodInfo.getDescription(),
        foodInfo.getImage(),
        menuItem.name(),
        userMenuName,
        menuItem.price().amount(),
        userPrice
    );
  }
}
