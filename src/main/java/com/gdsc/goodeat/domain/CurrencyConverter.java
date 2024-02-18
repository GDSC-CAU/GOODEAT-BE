package com.gdsc.goodeat.domain;

import static com.gdsc.goodeat.exception.CurrencyExceptionType.CURRENCY_RATE_ELEMENT_NOT_FOUND;
import static com.gdsc.goodeat.exception.CurrencyExceptionType.CURRENCY_RATE_SCRAPING_FAILED;

import com.gdsc.goodeat.exception.CurrencyException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class CurrencyConverter {

  private static final String GOOGLE_FINANCE_URL = "https://www.google.com/finance/quote/";

  public List<Double> convert(List<Double> originPriceList, String from, String to) {
    String url = buildUrl(from, to);
    String html = scrapHtml(url);
    Double exchangeRate = extractExchangeRate(html);

    List<Double> userPriceList = new ArrayList<>();

    for (Double originPrice : originPriceList) {
      userPriceList.add(originPrice * exchangeRate);
    }

    return userPriceList;
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
}
