package com.gdsc.goodeat.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FoodInfo {

  private String image;
  private String previewImage;
  private String description;

  public FoodInfo() {

  }
}
