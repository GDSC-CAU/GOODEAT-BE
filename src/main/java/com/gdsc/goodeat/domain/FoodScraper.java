package com.gdsc.goodeat.domain;

import static com.gdsc.goodeat.exception.FoodExceptionType.FOOD_NOT_FOUND;
import static com.gdsc.goodeat.exception.FoodExceptionType.FOOD_SCRAPING_FAILED;

import com.gdsc.goodeat.exception.FoodException;
import java.time.Duration;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FoodScraper implements AutoCloseable {
  private static final String BASE_URL = "https://www.tasteatlas.com/";
  private static final Duration DEFAULT_IMPLICIT_WAIT_DURATION = Duration.ofSeconds(5);
  private static final String SEARCH_INPUT_SELECTOR = "input[placeholder='Search locations or food']";
  private static final String FIRST_RESULT_SELECTOR = "img.food-image.ng-scope:not(.emblem-image)";
  private static final String IMAGE_SELECTOR = "img.img";
  private static final String DESCRIPTION_SELECTOR = "div.read-more--hidden.ng-scope";

  private final WebDriver driver;

  private final Logger log = LoggerFactory.getLogger(CurrencyConverter.class);

  public FoodScraper() {
    this.driver = new ChromeDriver();
  }

  public FoodInfo scrape(String foodName) {
    try {
      driver.get(BASE_URL);

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
      return extractFoodInfo();
    } catch (FoodException e) {
      log.info(e.exceptionType().getExceptionMessage()); // FOOD NOT FOUND
      return new FoodInfo();
    } catch (NoSuchElementException e) {
      throw new FoodException(FOOD_SCRAPING_FAILED);
    }
  }

  private WebElement getSearchResult() {
    WebElement firstResult;

    try {
      firstResult = driver.findElement(By.cssSelector(FIRST_RESULT_SELECTOR));
      return firstResult;
    } catch (NoSuchElementException e) {
      throw new FoodException(FOOD_NOT_FOUND); // Food Not Found
    }
  }

  private FoodInfo extractFoodInfo() {
    FoodInfo foodInfo = new FoodInfo();

    try {
      foodInfo.setImage(driver.findElement(By.cssSelector(IMAGE_SELECTOR)).getAttribute("src"));
    } catch (NoSuchElementException e) {
      log.info("IMG NOT FOUND");
    }

    try {
      foodInfo.setDescription(driver.findElement(By.cssSelector(DESCRIPTION_SELECTOR)).getText()
          .replaceAll("\\n{2,}", "\n"));
    } catch (NoSuchElementException e) {
      log.info("DESCRIPTION NOT FOUND");
    }

    return foodInfo;
  }

  @Override
  public void close() throws Exception {
    driver.quit();
  }

  @Data
  public static class FoodInfo {
    private String image;
    private String description;
  }
}