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
        new ReconfigureResponse("음식 설명1",
            "https://www.tasteatlas.com/images/dishes/fe1a26ac1abe4965abac2a042e3cc5ad.jpg?mw=1300",
            "https://www.tasteatlas.com/images/dishes/fe1a26ac1abe4965abac2a042e3cc5ad.jpg?mw=1300",
            "Bún Chả1", "분짜1", 10000.0, "2000.0 VND",
            10000.0, "₩ 10000"
            ),
        new ReconfigureResponse("음식 설명2",
            "https://www.tasteatlas.com/images/dishes/89e04888e4314a498cc90a0aa481369b.jpg?mw=1300",
            "https://www.tasteatlas.com/images/dishes/89e04888e4314a498cc90a0aa481369b.jpg?mw=1300",
            "Bún Chả2", "분짜2", 10000.0, "2000.0 VND",
            10000.0, "₩ 10000"),
        new ReconfigureResponse("음식 설명3",
            "https://www.tasteatlas.com/Images/Dishes/af87ccbe8eea49e7ae6016e22d0f6762.jpg?mw=1300",
            "https://www.tasteatlas.com/Images/Dishes/af87ccbe8eea49e7ae6016e22d0f6762.jpg?mw=1300",
            "Bún Chả3", "분짜3", 10000.0, "2000.0 VND",
            10000.0, "₩ 10000"),
        new ReconfigureResponse("음식 설명4",
            "https://www.tasteatlas.com/images/dishes/c50f70b06ace4660bf2f7580f5db1e50.jpg?mw=1300",
            "https://www.tasteatlas.com/images/dishes/c50f70b06ace4660bf2f7580f5db1e50.jpg?mw=1300",
            "Bún Chả4", "분짜4", 10000.0, "2000.0 VND",
            10000.0, "₩ 10000"),
        new ReconfigureResponse("음식 설명5",
            "https://www.tasteatlas.com/images/dishes/4218e605637047868740ccccb5edc2b6.jpg?mw=1300",
            "https://www.tasteatlas.com/images/dishes/4218e605637047868740ccccb5edc2b6.jpg?mw=1300",
            "Bún Chả5", "분짜5", 10000.0, "2000.0 VND",
            10000.0, "₩ 10000"),
        new ReconfigureResponse("음식 설명6",
            "https://www.tasteatlas.com/images/dishes/1a7882fb6dc64a5d9bd9bc085b6355c1.jpg?mw=1300",
            "https://www.tasteatlas.com/images/dishes/1a7882fb6dc64a5d9bd9bc085b6355c1.jpg?mw=1300",
            "Bún Chả6", "분짜6", 10000.0, "2000.0 VND",
            10000.0, "₩ 10000"),
        new ReconfigureResponse("음식 설명7",
            "https://www.tasteatlas.com/images/dishes/c3b400178b494c3e8f0a6640e274bdee.jpg?mw=1300",
            "https://www.tasteatlas.com/images/dishes/c3b400178b494c3e8f0a6640e274bdee.jpg?mw=1300",
            "Bún Chả7", "분짜7", 10000.0, "2000.0 VND",
            10000.0, "₩ 10000"),
        new ReconfigureResponse("음식 설명8",
            "https://www.tasteatlas.com/Images/Dishes/1c2092b4fc1f448a9d9aff0010d1e2f6.jpg?mw=1300",
            "https://www.tasteatlas.com/Images/Dishes/1c2092b4fc1f448a9d9aff0010d1e2f6.jpg?mw=1300",
            "Bún Chả8", "분짜8", 10000.0, "2000.0 VND",
            10000.0, "₩ 10000")
    );
    return ResponseEntity.ok(responses);
  }
}
