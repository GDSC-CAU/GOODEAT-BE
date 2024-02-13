package com.gdsc.goodeat.domain;

import static com.gdsc.goodeat.exception.CurrencyExceptionType.CURRENCY_RATE_SCRAPING_FAILED;
import static com.gdsc.goodeat.exception.CurrencyExceptionType.CURRENCY_RATE_ELEMENT_NOT_FOUND;

import com.gdsc.goodeat.exception.CurrencyException;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class CurrencyConverter {

  public double convert(double amount, String from, String to) {
    String url = buildUrl(from, to);
    String html = scrapHtml(url);
    double exchangeRate = extractExchangeRate(html);

    return amount * exchangeRate;
  }

  private String buildUrl(String from, String to) {
    return "https://www.google.com/finance/quote/" + from + "-" + to;
  }

  public String scrapHtml(String url) {
    try {
      return Jsoup.connect(url).get().html();
    } catch (IOException e) {
      throw new CurrencyException(CURRENCY_RATE_SCRAPING_FAILED);
    }
  }

  private double extractExchangeRate(String html) {
    Document doc = Jsoup.parse(html);
    Element exchangeRateElement = doc.selectFirst("div[class=YMlKec fxKbKc]");

    System.out.println(exchangeRateElement);
    if (exchangeRateElement != null) {
      String exchangeRateString = exchangeRateElement.text();
      return Double.parseDouble(exchangeRateString);
    } else {
      throw new CurrencyException(CURRENCY_RATE_ELEMENT_NOT_FOUND);
    }
  }
}
