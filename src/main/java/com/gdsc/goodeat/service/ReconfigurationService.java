package com.gdsc.goodeat.service;

import com.gdsc.goodeat.domain.Currency;
import com.gdsc.goodeat.domain.CurrencyConverter;
import com.gdsc.goodeat.domain.FoodScraper;
import com.gdsc.goodeat.domain.FoodScraper.FoodInfo;
import com.gdsc.goodeat.domain.Language;
import com.gdsc.goodeat.domain.MenuItem;
import com.gdsc.goodeat.domain.OcrReaderComposite;
import com.gdsc.goodeat.domain.Price;
import com.gdsc.goodeat.domain.TranslationClient;
import com.gdsc.goodeat.dto.ReconfigureRequest;
import com.gdsc.goodeat.dto.ReconfigureResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReconfigurationService {

  private final OcrReaderComposite readerComposite;
  private final TranslationClient translationClient;
  private final CurrencyConverter currencyConverter;
  private final FoodScraper foodScraper;

  /**
   * 1. 이미지 읽고, 2. 읽은 값 통해서 크롤링을 먼저하고 3. 이후, 크로링한값 + 메뉴 이름을 번역 4. 환율 계산
   */
  public List<ReconfigureResponse> reconfigure(final ReconfigureRequest request) {
    final Language originLanguage = Language.fromLanguageName(request.originLanguageName());
    final Language userLanguage = Language.fromLanguageName(request.userLanguageName());

    final Currency originCurrency = Currency.fromCurrencyName(request.originCurrencyName());
    final Currency userCurrency = Currency.fromCurrencyName(request.userCurrencyName());

    //menuName
    final List<MenuItem> menuItems = readerComposite.readMenu(
        originLanguage, request.base64EncodedImage()
    );
    //음식 정보 크롤링 : 조회 후 설명 번역
    final List<FoodInfo> foodInfos = createFoodInfos(menuItems, userLanguage);
    //환율 계산
    final List<Double> convertedPrices = convertCurrency(menuItems, originCurrency, userCurrency);
    //음식 이름 번역
    final List<String> translatedMenuNames = translatedMenuName(
        menuItems, originLanguage, userLanguage
    );

    final List<ReconfigureResponse> responses = new ArrayList<>();

    for (int i = 0; i < menuItems.size(); i++) {
      final ReconfigureResponse response = ReconfigureResponse.createResponse(
          foodInfos.get(i),
          menuItems.get(i),
          translatedMenuNames.get(i),
          convertedPrices.get(i)
      );
      responses.add(response);
    }

    return responses;
  }

  private List<String> translatedMenuName(
      final List<MenuItem> menuItems, final Language originLanguage, final Language userLanguage
  ) {
    return menuItems.stream()
        .map(menuItem -> translationClient.translate(originLanguage, userLanguage, menuItem.name()))
        .toList();
  }

  private List<Double> convertCurrency(
      final List<MenuItem> menuItems, final Currency originCurrency, final Currency userCurrency
  ) {
    final List<Double> prices = menuItems.stream()
        .map(MenuItem::price)
        .map(Price::amount)
        .toList();
    return currencyConverter
        .convert(prices, originCurrency.getCurrencyName(), userCurrency.getCurrencyName());
  }

  private List<FoodInfo> createFoodInfos(
      final List<MenuItem> menuItems, final Language userLanguage
  ) {
    final List<String> menuItemNames = menuItems.stream()
        .map(MenuItem::name)
        .toList();
    return foodScraper.scrape(menuItemNames).stream()
        .map(foodInfo -> new FoodInfo(
            foodInfo.getImage(),
            translationClient.translate(Language.ENGLISH, userLanguage, foodInfo.getDescription())
        )).toList();
  }
}
