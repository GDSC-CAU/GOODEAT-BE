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
//    final List<ReconfigureResponse> responses = reconfigurationService.reconfigure(request);
    //TODO : 임시로 뼈대 추가, 추후 API 연결하기
    final List<ReconfigureResponse> responses = List.of(
        new ReconfigureResponse("음식 설명", "음식 imageUrl", "Bún Chả",
            "분짜", 10000.0, 2000.0)
    );
    return ResponseEntity.ok(responses);
  }
}
