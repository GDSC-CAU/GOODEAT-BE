package com.gdsc.goodeat.fake;

import com.gdsc.goodeat.domain.FoodScrapper;
import com.gdsc.goodeat.external.SeleniumFoodScraper.FoodInfo;
import java.util.List;

public class FakeFoodScrapper implements FoodScrapper {

  @Override
  public List<FoodInfo> scrape(final List<String> foodList) {
    return List.of(
        new FoodInfo("Beef bourguignon image", "Beef bourguignon preview", "Beef bourguignon"),
        new FoodInfo("Foie gras image", "Foie gras preview", "Foie gras")
    );
  }
}
