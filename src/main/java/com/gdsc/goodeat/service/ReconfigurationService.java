package com.gdsc.goodeat.service;

import com.gdsc.goodeat.domain.TranslationClient;
import com.gdsc.goodeat.dto.ReconfigureRequest;
import com.gdsc.goodeat.dto.ReconfigureResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReconfigurationService {

  private final TranslationClient translationClient;

  public List<ReconfigureResponse> reconfigure(final ReconfigureRequest request) {
    return null;
  }
}
