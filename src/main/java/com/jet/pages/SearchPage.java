package com.jet.pages;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import java.util.List;

import com.jet.utils.logs.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class SearchPage extends BasePage {
  /**
   * Constructor
   */
  public SearchPage(WebDriver driver) {
    super(driver);
  }

  String searchResultPageURL = "https://www.lieferando.de/en/delivery/food";

  /**
   * Web Elements
   */
  By priceRangeIndicator = By.cssSelector("div[data-qa='mov-indicator-content']>span[data-qa='text']>span");
  By showMoreButton = By.cssSelector("button[data-qa='swipe-navigation-item']");
  By searchCategoriesTextBox = By.cssSelector("input[data-qa='cuisine-search-element']");
  By searchOutput = By.cssSelector("div[data-qa='cuisine-filter-modal-item-element']>div>div");
  By overlay = By.cssSelector("div[data-qa='cuisine-search']");
  By restHeader = By.cssSelector("h1[data-qa='restaurant-list-header']");
  By footer = By.cssSelector("footer[data-qa='footer']");

  /**
   *
   * @param value
   * @return Locator
   */
  By getPriceRangeRadio(String value) {
    return By.cssSelector("div[data-qa='radio']:has(input[data-qa='radio-element'][value='" + value + "'])");
  }

  /**
   * Page Methods
   */


  /**
   * Searches the restaurant category , then verify the applied category
   * @param category
   * @return
   * @throws InterruptedException
   */
  public HomePage selectRestaurantCategory(String category) throws InterruptedException {
    click(showMoreButton);
    wait.until(ExpectedConditions.visibilityOfElementLocated(overlay));
    writeText(searchCategoriesTextBox, category);
    Thread.sleep(2000);
    waitVisibility(searchOutput);
    List<WebElement> list = findList(searchOutput);
    for (WebElement a : list) {
      if (a.getText().replaceAll("[^A-Za-z]", "").equals(category)) {
        a.click();
        break;
      }
    }
    Assert.assertTrue(driver.findElement(restHeader).getText().contains("Italian"), "Restaurant category is not as excepted category");
    return new HomePage(driver);
  }

  /**
   * applies the price range filter according to provided value
   * @param value
   * @return
   * @throws NoSuchElementException
   */
  public SearchPage selectPriceRange(int value) throws NoSuchElementException{
    Log.info("Select Restaurants Price Range for Range of " + value);
    try {
      click(getPriceRangeRadio(String.valueOf(value)));
    }
    catch (NoSuchElementException e) {
      throw new NoSuchElementException("Invalid Value provided");
    }
    return this;
  }

  /**
   * Verify the restaurants Price range according to the applied price range filter
   * @param value
   * @return
   * @throws Exception
   */
  public HomePage verifyRestaurantsPriceRange(int value) throws Exception {
    Log.info("Getting Number Restaurants for the selected range");
    /**
     * Website takes time to load Price Ranges of closed Restaurant , So taking open restaurants numbers only
     */
    Log.info("Getting price range for all the searched restaurants");
    while (true) {
      scrollToElement();
      try {
        wait.until(ExpectedConditions.visibilityOfElementLocated(footer));
        break;
      }
      catch (NoSuchElementException | TimeoutException e) {
      }
    }
    List<WebElement> listOfPriceRangeIndicators = driver.findElements(priceRangeIndicator);
    for (WebElement priceIndicator : listOfPriceRangeIndicators) {
      String intValue = priceIndicator.getText().replaceAll("[^0-9]", "");
      Assert.assertTrue(Integer.parseInt(intValue) <= value, "Price Range is not as per the selected Range");
    }
    return new HomePage(driver);
  }

  /**
   * Assert the search result URL
   * @return
   */
  public SearchPage assertResultPage() {
    Log.info("Asserting that current page is Lieferando Result Page.");
    wait.until(ExpectedConditions.urlContains(searchResultPageURL));
    Assert.assertTrue(driver.getCurrentUrl().contains(searchResultPageURL), "Current URL is not as expected URL");
    return this;
  }

}