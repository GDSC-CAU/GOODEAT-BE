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
        new MenuItem("김치찌개", new Price(Currency.SOUTH_KOREAN_WON, 1000.0)),
        new MenuItem("제육볶음", new Price(Currency.SOUTH_KOREAN_WON, 2000.0)),
        new MenuItem("칼국수", new Price(Currency.SOUTH_KOREAN_WON, 3000.0)),
        new MenuItem("삼겹살", new Price(Currency.SOUTH_KOREAN_WON, 4000.0)),
        new MenuItem("목살", new Price(Currency.SOUTH_KOREAN_WON, 5000.0)),
        new MenuItem("샤브샤브", new Price(Currency.SOUTH_KOREAN_WON, 6000.0))
    );
  }

  @Override
  public Language supportedLanguage() {
    return Language.KOREAN;
  }
}
