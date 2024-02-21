package com.gdsc.goodeat.domain;

import java.util.List;

public interface FoodScrapper {

  List<FoodInfo> scrap(final List<String> foods);
}
