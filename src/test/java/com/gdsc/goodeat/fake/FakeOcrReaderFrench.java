package com.gdsc.goodeat.fake;

import com.gdsc.goodeat.domain.Currency;
import com.gdsc.goodeat.domain.Language;
import com.gdsc.goodeat.domain.MenuItem;
import com.gdsc.goodeat.domain.OcrReader;
import com.gdsc.goodeat.domain.Price;
import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class FakeOcrReaderFrench implements OcrReader {

  @Override
  public List<MenuItem> read(final String base64encodedImage) {
    return List.of(
        new MenuItem("Beef bourguignon", new Price(Currency.EURO, 2000.0)),
        new MenuItem("Foie gras", new Price(Currency.EURO, 1000.0))
    );
  }

  @Override
  public Language supportedLanguage() {
    return Language.FRENCH;
  }
}
