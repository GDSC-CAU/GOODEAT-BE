package com.gdsc.goodeat.controller;

import com.gdsc.goodeat.dto.ReconfigureRequest;
import com.gdsc.goodeat.dto.ReconfigureResponse;
import com.gdsc.goodeat.service.ReconfigurationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReconfigurationController {

  private final ReconfigurationService reconfigurationService;

  @PostMapping("/reconfigure")
  public ResponseEntity<List<ReconfigureResponse>> reconfigure(
      @RequestBody final ReconfigureRequest request
  ) {
    final List<ReconfigureResponse> responses = reconfigurationService.reconfigure(request);
    return ResponseEntity.ok(responses);
  }
}
