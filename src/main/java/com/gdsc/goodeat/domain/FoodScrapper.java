package com.gdsc.goodeat.domain;

import com.gdsc.goodeat.external.SeleniumFoodScraper.FoodInfo;
import java.util.List;

public interface FoodScrapper {

  List<FoodInfo> scrape(final List<String> foodList);
}
