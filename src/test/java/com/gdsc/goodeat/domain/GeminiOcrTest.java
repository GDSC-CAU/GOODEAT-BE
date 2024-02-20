package com.gdsc.goodeat.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdsc.goodeat.external.GeminiOcr;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

class GeminiOcrTest {

  @Test
  @Disabled
  void OCR로_읽은_메뉴를_객체로_변환해준다() throws IOException {
    //given`
    final GeminiOcr geminiOcr = new GeminiOcr(
        "secret/google-cloud-credentials.json", new ObjectMapper()
    );

    //when
    final Scanner scanner = new Scanner(new ClassPathResource("image.txt").getInputStream());
    final String encodedImage = scanner.nextLine();

    final List<MenuItem> actual = geminiOcr.read(encodedImage);
    final List<MenuItem> expected = List.of(
        new MenuItem("멜팅 스테이크 파스타", 17.0),
        new MenuItem("베이컨 까르보나라", 17.0),
        new MenuItem("게살 오이스터 파스타", 17.0),
        new MenuItem("해산물 크림 파스타", 17.0),
        new MenuItem("해산물 토마토 파스타", 17.0),
        new MenuItem("게살 오이스터 리조또", 17.5),
        new MenuItem("베이컨 크림 리조또", 16.5),
        new MenuItem("해산물 토마토 리조또", 16.5),
        new MenuItem("김치 리조또", 15.5),
        new MenuItem("토마토 미트 치즈 피자", 17.0),
        new MenuItem("불고구마 피자", 17.0),
        new MenuItem("게살 치즈 피자", 17.0),
        new MenuItem("연어 샐러드", 12.5),
        new MenuItem("치킨 텐더 샐러드", 12.5),
        new MenuItem("리코타 그린 샐러드", 12.5),
        new MenuItem("샐러드 추가", 4.5)
    );

    //then
    assertThat(actual)
        .usingRecursiveFieldByFieldElementComparator()
        .containsExactlyInAnyOrderElementsOf(expected);
  }
}
