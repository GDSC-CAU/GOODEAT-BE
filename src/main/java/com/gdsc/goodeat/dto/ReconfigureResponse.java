package com.gdsc.goodeat.dto;

import com.gdsc.goodeat.domain.CurrencyConverter.PriceInfo;
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
  private final String previewImageUrl;
  private final String imageUrl;
  private final String originMenuName;
  private final String userMenuName;
  private final Double originPrice;
  private final String originPriceWithCurrencyUnit;
  private final Double userPrice;
  private final String userPriceWithCurrencyUnit;

  public static ReconfigureResponse createResponse(
      final FoodInfo foodInfo, final MenuItem menuItem,
      final String userMenuName, final PriceInfo priceInfo
  ) {
    return new ReconfigureResponse(
        foodInfo.getDescription(),
        foodInfo.getPreviewImage(),
        foodInfo.getImage(),
        menuItem.name(),
        userMenuName,
        priceInfo.getOriginPrice(),
        priceInfo.getOriginPriceWithCurrencyUnit(),
        priceInfo.getUserPrice(),
        priceInfo.getUserPriceWithCurrencyUnit()
    );
  }
}
