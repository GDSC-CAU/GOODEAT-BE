package com.gdsc.goodeat.controller;

import com.gdsc.goodeat.dto.ScriptGenerateRequest;
import com.gdsc.goodeat.dto.ScriptResponse;
import com.gdsc.goodeat.service.ScriptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScriptController {

  private final ScriptService scriptService;

  @PostMapping("/script")
  public ResponseEntity<ScriptResponse> generateScript(@RequestBody final ScriptGenerateRequest request) {
    final ScriptResponse response = scriptService.generateScript(request);
    return ResponseEntity.ok(response);
  }
}
