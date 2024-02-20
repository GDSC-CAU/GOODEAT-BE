package com.gdsc.goodeat.domain;

import static com.gdsc.goodeat.exception.CurrencyExceptionType.CURRENCY_RATE_ELEMENT_NOT_FOUND;
import static com.gdsc.goodeat.exception.CurrencyExceptionType.CURRENCY_RATE_SCRAPING_FAILED;

import com.gdsc.goodeat.exception.CurrencyException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class CurrencyConverter {

  private static final String GOOGLE_FINANCE_URL = "https://www.google.com/finance/quote/";

  public List<PriceInfo> convert(List<Double> originPriceList, String from, String to) {
    String url = buildUrl(from, to);
    String html = scrapHtml(url);
    Double exchangeRate = extractExchangeRate(html);

    List<PriceInfo> priceList = new ArrayList<>();

    for (Double originPrice : originPriceList) {
      Double userPrice = Double.valueOf(String.format("%.2f", originPrice * exchangeRate));

      priceList.add(new PriceInfo(
          originPrice,
          addCurrencyUnit(originPrice, from),
          userPrice,
          addCurrencyUnit(userPrice, to)));
    }

    return priceList;
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
