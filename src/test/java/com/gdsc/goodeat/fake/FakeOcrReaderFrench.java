package com.gdsc.goodeat.fake;

import com.gdsc.goodeat.domain.MenuItem;
import com.gdsc.goodeat.domain.OcrReader;
import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class FakeOcrReaderFrench implements OcrReader {

  @Override
  public List<MenuItem> read(final String base64encodedImage) {
    return List.of(
        new MenuItem("Beef bourguignon", 2000.0),
        new MenuItem("Foie gras", 1000.0)
    );
  }
}
