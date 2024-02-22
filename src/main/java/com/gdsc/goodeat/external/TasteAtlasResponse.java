package com.gdsc.goodeat.external;

import static com.gdsc.goodeat.exception.FoodExceptionType.FOOD_NOT_FOUND;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gdsc.goodeat.exception.FoodException;
import java.util.List;
import lombok.Data;

@Data
public class TasteAtlasResponse {

  @JsonProperty("CustomItems")
  private List<Item> customItems;

  @JsonProperty("Items")
  private List<Item> items;

  public Item findFirst() {
    return customItems.stream()
        .findFirst()
        .orElseGet(this::findItems);
  }

  private Item findItems() {
    return items.stream()
        .findFirst()
        .orElseThrow(() -> new FoodException(FOOD_NOT_FOUND));
  }

  @Data
  public static class Item {

    @JsonProperty("EntityId")
    private int EntityId;
    @JsonProperty("EntityId2")
    private Integer EntityId2;
    @JsonProperty("Name")
    private String Name;
    @JsonProperty("OtherName")
    private String OtherName;
    @JsonProperty("Subtitle")
    private String Subtitle;
    @JsonProperty("EntityType")
    private int EntityType;
    @JsonProperty("PreviewImage")
    private PreviewImage PreviewImage;
    @JsonProperty("UrlLink")
    private String UrlLink;
    @JsonProperty("TypeOverride")
    private String TypeOverride;

    public String generateFullName() {
      if (OtherName == null) {
        return Name;
      }
      return String.format("%s (%s)", Name, OtherName);
    }
  }

  @Data
  public static class PreviewImage {

    @JsonProperty("Image")
    private String Image;
    @JsonProperty("Source")
    private String Source;
    @JsonProperty("SourceUrl")
    private String SourceUrl;
  }
}
