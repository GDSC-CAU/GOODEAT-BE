package com.gdsc.goodeat.domain;

import static com.gdsc.goodeat.exception.CurrencyExceptionType.CURRENCY_RATE_ELEMENT_NOT_FOUND;
import static com.gdsc.goodeat.exception.CurrencyExceptionType.CURRENCY_RATE_SCRAPING_FAILED;

import com.gdsc.goodeat.exception.CurrencyException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrencyConverter {

  private static final String GOOGLE_FINANCE_URL = "https://www.google.com/finance/quote/";

  private final CurrencyPriceConcater currencyPriceConcater;

  public List<PriceInfo> convert(List<Double> originPrices, String from, String to) {
    final String url = buildUrl(from, to);
    final String html = scrapHtml(url);
    final Double exchangeRate = extractExchangeRate(html);

    return originPrices.stream()
        .map(originPrice -> generatePriceInfo(from, to, originPrice, exchangeRate))
        .toList();
  }

  private PriceInfo generatePriceInfo(
      final String from, final String to, final Double originPrice, final Double exchangeRate
  ) {
    final Double userPrice = originPrice * exchangeRate;
    return new PriceInfo(
        originPrice, currencyPriceConcater.concatPriceWithCurrency(from, originPrice)
        , userPrice, currencyPriceConcater.concatPriceWithCurrency(to, userPrice)
    );
  }

  private String buildUrl(String from, String to) {
    return GOOGLE_FINANCE_URL + from + "-" + to;
  }

  public String scrapHtml(String url) {
    try {
      return Jsoup.connect(url).get().html();
    } catch (IOException e) {
      throw new CurrencyException(CURRENCY_RATE_SCRAPING_FAILED);
    }
  }

  private Double extractExchangeRate(String html) {
    Document doc = Jsoup.parse(html);
    Element exchangeRateElement = doc.selectFirst("div[class=YMlKec fxKbKc]");

    if (exchangeRateElement != null) {
      String exchangeRateString = exchangeRateElement.text()
          .replaceAll(",", "");
      return Double.parseDouble(exchangeRateString);
    } else {
      throw new CurrencyException(CURRENCY_RATE_ELEMENT_NOT_FOUND);
    }
  }

  private String addCurrencyUnit(Double price, String currency) {
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();

    // 통화 코드 설정
    currencyFormatter.setCurrency(Currency.getInstance(currency));

    return currencyFormatter.getCurrency().getSymbol() + " " + String.format("%.2f", price);
  }

  @Data
  @AllArgsConstructor
  public static class PriceInfo {

    private Double originPrice;
    private String originPriceWithCurrencyUnit;
    private Double userPrice;
    private String userPriceWithCurrencyUnit;
  }
}
