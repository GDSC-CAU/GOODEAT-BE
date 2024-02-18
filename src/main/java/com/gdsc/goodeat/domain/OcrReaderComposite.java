package com.gdsc.goodeat.domain;

import static com.gdsc.goodeat.exception.OcrReaderExceptionType.OCR_READER_NOT_FOUND;

import com.gdsc.goodeat.exception.OcrReaderException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OcrReaderComposite {

  private final Map<Language, OcrReader> ocrReaderMap;

  public OcrReaderComposite(final List<OcrReader> scriptGenerators) {
    ocrReaderMap = scriptGenerators.stream()
        .collect(Collectors.toMap(OcrReader::supportedLanguage, Function.identity()));
  }

  public List<MenuItem> readMenu(final Language language, final String base64encodedImage) {
    final Optional<OcrReader> scriptGenerator = Optional.ofNullable(ocrReaderMap.get(language));

    return scriptGenerator
        .map(reader -> reader.read(base64encodedImage))
        .orElseThrow(() -> new OcrReaderException(OCR_READER_NOT_FOUND));
  }
}
