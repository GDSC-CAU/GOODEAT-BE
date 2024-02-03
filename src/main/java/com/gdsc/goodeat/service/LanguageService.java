package com.gdsc.goodeat.service;

import com.gdsc.goodeat.domain.Language;
import com.gdsc.goodeat.dto.LanguageResponse;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class LanguageService {

  public List<LanguageResponse> findAllLanguages() {
    return Arrays.stream(Language.values())
        .map(LanguageResponse::from)
        .toList();
  }
}
