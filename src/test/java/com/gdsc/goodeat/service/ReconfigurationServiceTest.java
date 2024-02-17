package com.gdsc.goodeat.service;

import com.gdsc.goodeat.domain.TranslationClient;
import com.gdsc.goodeat.dto.ReconfigureRequest;
import com.gdsc.goodeat.dto.ReconfigureResponse;
import com.gdsc.goodeat.support.ServiceTest;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ReconfigurationServiceTest extends ServiceTest {

  @Autowired
  private ReconfigurationService reconfigurationService;

  @Test
  void 메뉴판을_재구성한_결과를_반환한다() {
    final ReconfigureRequest request = new ReconfigureRequest(
        "French", "Korean",
        "Euro", "South Korean won",
        "FakeOcrReaderFrench를 쓸거기에 무의미한 이미지 경로"
    );

    //TODO: 아직 미완성 추가적인 작업 필요
    final List<ReconfigureResponse> actual
        = reconfigurationService.reconfigure(request);

    System.out.println(actual);
  }
}