package com.gdsc.goodeat.external;

import static com.gdsc.goodeat.exception.FoodExceptionType.FOOD_NOT_FOUND;
import static com.gdsc.goodeat.exception.FoodExceptionType.FOOD_SCRAPING_FAILED;

import com.gdsc.goodeat.domain.FoodInfo;
import com.gdsc.goodeat.domain.FoodScrapper;
import com.gdsc.goodeat.exception.FoodException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;

@Deprecated
@RequiredArgsConstructor
public class SeleniumFoodScraper implements FoodScrapper {

  public static final String FOOD_DES_NOT_FOUND = "";
  private static final String TASTEATLAS_URL = "https://www.tasteatlas.com/";
  private static final String FOOD_IMG_NOT_FOUND_URL = "https://storage.googleapis.com/goodeat/food_image_not_found.jpg";
  private static final Duration DEFAULT_IMPLICIT_WAIT_DURATION = Duration.ofSeconds(5);
  private static final String SEARCH_INPUT_SELECTOR = "input[placeholder='Search locations or food']";
  private static final String FIRST_RESULT_SELECTOR = "img.food-image.ng-scope:not(.emblem-image)";
  private static final String IMAGE_SELECTOR = "img.img";
  private static final String DESCRIPTION_SELECTOR = "div.read-more--hidden.ng-scope";

  private final Map<String, FoodInfo> foodInfoCache = new ConcurrentHashMap<>();

  @Override
  public List<FoodInfo> scrape(final List<String> foods) {
    final WebDriver driver = gerateWebDriver();

    final List<FoodInfo> foodInfos = new ArrayList<>();

    for (String foodName : foods) {
      final FoodInfo foodInfo = foodInfoCache.computeIfAbsent(
          foodName,
          name -> scrapFoodInfo(name, driver)
      );
      foodInfos.add(foodInfo);
    }

    driver.quit();

    return foodInfos;
  }

  private FoodInfo scrapFoodInfo(final String foodName, final WebDriver driver) {
    try {
      driver.get(TASTEATLAS_URL);

      // input food name & search
      WebElement searchInput = driver.findElement(By.cssSelector(SEARCH_INPUT_SELECTOR));
      searchInput.click();
      searchInput.sendKeys(foodName);
      driver.manage().timeouts().implicitlyWait(DEFAULT_IMPLICIT_WAIT_DURATION);

      // get search result & enter page
      WebElement searchResult = getSearchResult(driver);
      System.out.println(searchResult.getAttribute("src").replace("?mw=150", ""));
      String preview = searchResult.getAttribute("src").replace("?mw=150", "");
      Actions act = new Actions(driver);
      act.scrollToElement(searchResult).click().perform();
      driver.manage().timeouts().implicitlyWait(DEFAULT_IMPLICIT_WAIT_DURATION);

      // extract info
      final FoodInfo foodInfo = extractFoodInfo(driver);
      foodInfo.setPreviewImage(preview);
      return foodInfo;
    } catch (FoodException e) {
      return new FoodInfo(FOOD_IMG_NOT_FOUND_URL, FOOD_IMG_NOT_FOUND_URL, FOOD_DES_NOT_FOUND);
    } catch (NoSuchElementException e) {
      driver.quit();
      throw new FoodException(FOOD_SCRAPING_FAILED);
    }
  }

  private static WebDriver gerateWebDriver() {
    final FirefoxOptions options = new FirefoxOptions();
    options.addArguments("--headless");
    return new FirefoxDriver(options);
  }

  private WebElement getSearchResult(final WebDriver driver) {
    try {
      return driver.findElement(By.cssSelector(FIRST_RESULT_SELECTOR));
    } catch (NoSuchElementException e) {
      throw new FoodException(FOOD_NOT_FOUND); // Food Not Found
    }
  }

  private FoodInfo extractFoodInfo(final WebDriver driver) {
    FoodInfo foodInfo = new FoodInfo();

    try {
      foodInfo.setImage(driver.findElement(By.cssSelector(IMAGE_SELECTOR)).getAttribute("src")
          .replace("?mw=1300", ""));
    } catch (NoSuchElementException | NullPointerException e) {
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
}