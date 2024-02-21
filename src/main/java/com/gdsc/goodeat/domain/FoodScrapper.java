package com.gdsc.goodeat.domain;

import java.util.List;

public interface FoodScrapper {

  List<FoodInfo> scrape(final List<String> foods);
}
