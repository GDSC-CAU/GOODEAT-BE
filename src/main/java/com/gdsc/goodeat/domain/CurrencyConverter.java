package com.gdsc.goodeat.domain;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class CurrencyConverter {

  public double convert(double amount, String from, String to) {
    String url = buildUrl(from, to);
    String html = crawlHtml(url);
    double exchangeRate = extractExchangeRate(html);

    return amount * exchangeRate;
  }

  private String buildUrl(String from, String to) {
    return "https://www.google.com/finance/quote/" + from + "-" + to;
  }

  public String crawlHtml(String url) {
    try {
      return Jsoup.connect(url).get().html();
    } catch (IOException e) {
      e.printStackTrace(); // 추후 수정
      return null; // 추후 수정
//      throw new CurrencyConversionException("Error while crawling HTML from " + url, e);
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
      return -1; // 추후 수정
//      throw new CurrencyConversionException("Exchange rate not found in HTML");
    }
  }
}
