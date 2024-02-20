package com.gdsc.goodeat.fake;

import com.gdsc.goodeat.domain.MenuItem;
import com.gdsc.goodeat.domain.OcrReader;
import java.util.List;

public class FakeOcrReader implements OcrReader {

  @Override
  public List<MenuItem> read(final String base64encodedImage) {
    return List.of(
        new MenuItem("김치찌개", 1000.0),
        new MenuItem("제육볶음", 2000.0),
        new MenuItem("칼국수", 3000.0),
        new MenuItem("삼겹살", 4000.0),
        new MenuItem("목살", 5000.0),
        new MenuItem("샤브샤브", 6000.0)
    );
  }
}
