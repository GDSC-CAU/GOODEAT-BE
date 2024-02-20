package com.gdsc.goodeat.fake;

import com.gdsc.goodeat.domain.FoodInfo;
import com.gdsc.goodeat.domain.FoodScrapper;
import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class FakeFoodScrapper implements FoodScrapper {

  @Override
  public List<FoodInfo> scrape(final List<String> foodList) {
    return List.of(
        new FoodInfo("Beef bourguignon image", "Beef bourguignon preview", "Beef bourguignon"),
        new FoodInfo("Foie gras image", "Foie gras preview", "Foie gras")
    );
  }
}
