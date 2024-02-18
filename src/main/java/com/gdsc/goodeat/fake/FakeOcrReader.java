package com.gdsc.goodeat.fake;

import com.gdsc.goodeat.domain.Currency;
import com.gdsc.goodeat.domain.Language;
import com.gdsc.goodeat.domain.MenuItem;
import com.gdsc.goodeat.domain.OcrReader;
import com.gdsc.goodeat.domain.Price;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class FakeOcrReader implements OcrReader {

  @Override
  public List<MenuItem> read(final String base64encodedImage) {
    return List.of(
        new MenuItem("Moksal", new Price(Currency.SOUTH_KOREAN_WON, 1000.0)),
        new MenuItem("Jeyuk bokkeum", new Price(Currency.SOUTH_KOREAN_WON, 2000.0)),
        new MenuItem("Kalguksu", new Price(Currency.SOUTH_KOREAN_WON, 3000.0)),
        new MenuItem("Kimchi jjigae", new Price(Currency.SOUTH_KOREAN_WON, 4000.0)),
        new MenuItem("Shabu-shabu", new Price(Currency.SOUTH_KOREAN_WON, 6000.0))
    );
  }

  @Override
  public Language supportedLanguage() {
    return Language.KOREAN;
  }
}
