package com.gdsc.goodeat.domain;

import static com.gdsc.goodeat.exception.FoodExceptionType.FOOD_NOT_FOUND;
import static com.gdsc.goodeat.exception.FoodExceptionType.FOOD_SCRAPING_FAILED;

import com.gdsc.goodeat.exception.FoodException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;

@Component
public class FoodScraper {
  private static final String TASTEATLAS_URL = "https://www.tasteatlas.com/";
  private static final String FOOD_IMG_NOT_FOUND_URL = "https://storage.googleapis.com/goodeat/food_image_not_found.jpg";
  private static final Duration DEFAULT_IMPLICIT_WAIT_DURATION = Duration.ofSeconds(5);
  private static final String SEARCH_INPUT_SELECTOR = "input[placeholder='Search locations or food']";
  private static final String FIRST_RESULT_SELECTOR = "img.food-image.ng-scope:not(.emblem-image)";
  private static final String IMAGE_SELECTOR = "img.img";
  private static final String DESCRIPTION_SELECTOR = "div.read-more--hidden.ng-scope";

  private final WebDriver driver;


  public FoodScraper() {
    this.driver = new ChromeDriver();
  }

  public List<FoodInfo> scrape(List<String> foodList) {
    List<FoodInfo> foodInfoList = new ArrayList<>();

    for (String foodName : foodList) {
      try {
        driver.get(TASTEATLAS_URL);

        // input food name & search
        WebElement searchInput = driver.findElement(By.cssSelector(SEARCH_INPUT_SELECTOR));
        searchInput.click();
        searchInput.sendKeys(foodName);
        driver.manage().timeouts().implicitlyWait(DEFAULT_IMPLICIT_WAIT_DURATION);

        // get search result & enter page
        WebElement searchResult = getSearchResult();
        searchResult.click();
        driver.manage().timeouts().implicitlyWait(DEFAULT_IMPLICIT_WAIT_DURATION);

        // extract info
        foodInfoList.add(extractFoodInfo());
      } catch (FoodException e) {
        foodInfoList.add(new FoodInfo(FOOD_IMG_NOT_FOUND_URL, ""));
      } catch (NoSuchElementException e) {
        driver.quit();
        throw new FoodException(FOOD_SCRAPING_FAILED);
      }
    }

    driver.quit();

    return foodInfoList;
  }

  private WebElement getSearchResult() {
    try {
      return driver.findElement(By.cssSelector(FIRST_RESULT_SELECTOR));
    } catch (NoSuchElementException e) {
      throw new FoodException(FOOD_NOT_FOUND); // Food Not Found
    }
  }

  private FoodInfo extractFoodInfo() {
    FoodInfo foodInfo = new FoodInfo();

    try {
      foodInfo.setImage(driver.findElement(By.cssSelector(IMAGE_SELECTOR)).getAttribute("src"));
    } catch (NoSuchElementException e) {
      foodInfo.setImage(FOOD_IMG_NOT_FOUND_URL);
    }

    try {
      foodInfo.setDescription(driver.findElement(By.cssSelector(DESCRIPTION_SELECTOR)).getText()
          .replaceAll("\\n{2,}", "\n"));
    } catch (NoSuchElementException e) {
      foodInfo.setDescription("");
    }

    return foodInfo;
  }

  @Data
  @AllArgsConstructor
  public static class FoodInfo {
    private String image;
    private String description;

    public FoodInfo() {

    }
  }
}