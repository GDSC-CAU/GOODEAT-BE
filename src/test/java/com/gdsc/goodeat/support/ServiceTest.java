package com.gdsc.goodeat.support;

import static org.mockito.ArgumentMatchers.any;

import com.gdsc.goodeat.domain.FoodScraper;
import com.gdsc.goodeat.domain.FoodScraper.FoodInfo;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class ServiceTest {

  @MockBean
  private FoodScraper foodScraper;

  @BeforeEach
  void setUp() {
    Mockito.when(foodScraper.scrape(any())).thenReturn(List.of(
        new FoodInfo("김치찌개", "김치찌개", "김치찌개 설명"),
        new FoodInfo("제육볶음", "제육볶음", "제육볶음 설명"),
        new FoodInfo("칼국수", "칼국수", "칼국수 설명"),
        new FoodInfo("삼겹살", "삼겹살", "삼겹살 설명"),
        new FoodInfo("목살", "목살", "목살 설명"),
        new FoodInfo("샤브샤브", "샤브샤브", "샤브샤브 설명")
    ));
  }
}
