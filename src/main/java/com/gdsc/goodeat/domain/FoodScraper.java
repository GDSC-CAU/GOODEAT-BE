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
import org.openqa.selenium.chrome.ChromeOptions;
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
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--headless");
    this.driver = new ChromeDriver(options);
  }

  public List<FoodInfo> scrape(final List<String> foodList) {
    List<FoodInfo> foodInfoList = new ArrayList<>();

    for (String foodName : foodList) {
      FoodInfo foodInfo;
      try {
        driver.get(TASTEATLAS_URL);

        // input food name & search
        WebElement searchInput = driver.findElement(By.cssSelector(SEARCH_INPUT_SELECTOR));
        searchInput.click();
        searchInput.sendKeys(foodName);
        driver.manage().timeouts().implicitlyWait(DEFAULT_IMPLICIT_WAIT_DURATION);

        // get search result & enter page
        WebElement searchResult = getSearchResult();
        System.out.println(searchResult.getAttribute("src").replace("?mw=150", ""));
        String preview = searchResult.getAttribute("src").replace("?mw=150", "");
        searchResult.click();
        driver.manage().timeouts().implicitlyWait(DEFAULT_IMPLICIT_WAIT_DURATION);

        // extract info
        foodInfo = extractFoodInfo();
        foodInfo.setPreviewImage(preview);
      } catch (FoodException e) {
        foodInfo = new FoodInfo(FOOD_IMG_NOT_FOUND_URL, FOOD_IMG_NOT_FOUND_URL, "");
      } catch (NoSuchElementException e) {
        driver.quit();
        throw new FoodException(FOOD_SCRAPING_FAILED);
      }

      foodInfoList.add(foodInfo);
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
      foodInfo.setImage(driver.findElement(By.cssSelector(IMAGE_SELECTOR)).getAttribute("src")
          .replace("?mw=1300", ""));
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
    private String previewImage;
    private String description;

    public FoodInfo() {

    }
  }
}