package com.gdsc.goodeat.domain;

import static com.gdsc.goodeat.exception.CurrencyExceptionType.CURRENCY_RATE_SCRAPING_FAILED;
import static com.gdsc.goodeat.exception.CurrencyExceptionType.CURRENCY_RATE_ELEMENT_NOT_FOUND;

import com.gdsc.goodeat.dto.ReconfigureResponse;
import com.gdsc.goodeat.exception.CurrencyException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class CurrencyConverter {

  public List<ReconfigureResponse> convert(List<ReconfigureResponse> origin, String from, String to) {
    String url = buildUrl(from, to);
    String html = scrapHtml(url);
    Double exchangeRate = extractExchangeRate(html);

    List<ReconfigureResponse> converted = new ArrayList<>();

    for(int i=0; i < origin.size(); i++) {
      ReconfigureResponse rec = origin.get(i);
      ReconfigureResponse response = new ReconfigureResponse(
          rec.description(),
          rec.imageUrl(),
          rec.originMenuName(),
          rec.userMenuName(),
          rec.originPrice(),
          rec.originPrice() * exchangeRate
      );
      converted.add(response);
    }

    return converted;
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

  private Double extractExchangeRate(String html) {
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
