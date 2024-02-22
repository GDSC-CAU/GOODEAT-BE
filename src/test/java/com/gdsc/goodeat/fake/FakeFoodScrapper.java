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
  public List<FoodInfo> scrap(final List<String> foods) {
    return List.of(
        new FoodInfo("Beef bourguignon", "Beef bourguignon image", "Beef bourguignon preview",
            "Beef bourguignon des"),
        new FoodInfo("Foie gras", "Foie gras image", "Foie gras preview", "Foie gras des")
    );
  }
}
