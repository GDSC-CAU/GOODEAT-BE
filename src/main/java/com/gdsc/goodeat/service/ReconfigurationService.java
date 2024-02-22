package com.gdsc.goodeat.service;

import com.gdsc.goodeat.domain.Currency;
import com.gdsc.goodeat.domain.CurrencyConverter;
import com.gdsc.goodeat.domain.CurrencyConverter.PriceInfo;
import com.gdsc.goodeat.domain.FoodInfo;
import com.gdsc.goodeat.domain.FoodScrapper;
import com.gdsc.goodeat.domain.Language;
import com.gdsc.goodeat.domain.MenuItem;
import com.gdsc.goodeat.domain.OcrReader;
import com.gdsc.goodeat.domain.TranslationClient;
import com.gdsc.goodeat.dto.ReconfigureRequest;
import com.gdsc.goodeat.dto.ReconfigureResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReconfigurationService {

  private final TranslationClient translationClient;
  private final CurrencyConverter currencyConverter;
  private final FoodScrapper foodScraper;
  private final OcrReader ocrReader;

  /**
   * 1. 이미지 읽고, 2. 읽은 값 통해서 크롤링을 먼저하고 3. 이후, 크로링한값 + 메뉴 이름을 번역 4. 환율 계산
   */
  public List<ReconfigureResponse> reconfigure(final ReconfigureRequest request) {
    final Language originLanguage = Language.fromLanguageName(request.originLanguageName());
    final Language userLanguage = Language.fromLanguageName(request.userLanguageName());

    final Currency originCurrency = Currency.fromCurrencyName(request.originCurrencyName());
    final Currency userCurrency = Currency.fromCurrencyName(request.userCurrencyName());

    //menuName
    log.info("ocr 시작");
    final List<MenuItem> menuItems = ocrReader.read(request.base64EncodedImage());
    log.info("ocr 끝");
    //음식 정보 크롤링 : 조회 후 설명 번역
    final List<FoodInfo> foodInfos = createFoodInfos(menuItems, userLanguage, originLanguage);
    //환율 계산
    final List<PriceInfo> convertedPrices = convertCurrency(
        menuItems, originCurrency, userCurrency
    );
    //음식 이름 번역
    final List<String> translatedMenuNames = translatedMenuName(
        foodInfos, originLanguage, userLanguage
    );

    final List<ReconfigureResponse> responses = new ArrayList<>();

    for (int i = 0; i < menuItems.size(); i++) {
      final ReconfigureResponse response = ReconfigureResponse.createResponse(
          foodInfos.get(i),
          translatedMenuNames.get(i),
          convertedPrices.get(i)
      );
      responses.add(response);
    }

    return responses;
  }

  private List<String> translatedMenuName(
      final List<FoodInfo> foodInfos, final Language originLanguage, final Language userLanguage
  ) {
    log.info("번역 시작");
    final List<String> result = foodInfos.stream()
        .map(foodInfo -> translationClient.translate(originLanguage, userLanguage, foodInfo.name()))
        .toList();
    log.info("번역 끝");
    return result;
  }

  private List<PriceInfo> convertCurrency(
      final List<MenuItem> menuItems, final Currency originCurrency, final Currency userCurrency
  ) {
    log.info("환율계산 시작");
    final List<Double> prices = menuItems.stream()
        .map(MenuItem::price)
        .toList();
    log.info("환율계산 끝");
    return currencyConverter.convert(
        prices, originCurrency.getISO4217Code(), userCurrency.getISO4217Code()
    );
  }

  private List<FoodInfo> createFoodInfos(
      final List<MenuItem> menuItems, final Language userLanguage, final Language originLanguage
  ) {
    log.info("이미지 크롤링 시작");
    //TODO: 번역 API 최적화 고민
    final List<String> menuItemNames = menuItems.stream()
        .map(MenuItem::name)
        .map(name -> translationClient.translate(originLanguage, Language.ENGLISH, name))
        .toList();
    //TODO: 스크랩 최적화 고민
    final List<FoodInfo> result = foodScraper.scrap(menuItemNames).stream()
        .map(foodInfo -> new FoodInfo(
            foodInfo.name(),
            foodInfo.image(),
            foodInfo.previewImage(),
            translationClient.translate(Language.ENGLISH, userLanguage, foodInfo.description())
        )).toList();
    log.info("이미지 크롤링 끝");
    return result;
  }
}
