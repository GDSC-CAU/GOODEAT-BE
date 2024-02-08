package com.gdsc.goodeat.controller;

import com.gdsc.goodeat.dto.LanguageResponse;
import com.gdsc.goodeat.service.LanguageService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LanguageController {

  private final LanguageService languageService;

  public LanguageController(final LanguageService languageService) {
    this.languageService = languageService;
  }

  @GetMapping("/lagnuages")
  public ResponseEntity<List<LanguageResponse>> getLanguages() {
    final List<LanguageResponse> languageResponses = languageService.findAllLanguages();
    return ResponseEntity.ok(languageResponses);
  }
}
