package com.jet.pages;

import com.jet.utils.logs.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class HomePage extends BasePage {
  /**
   * Constructor
   */
  public HomePage(WebDriver driver) {
    super(driver);
  }

  /**
   * Variables
   */
  String baseURL = "https://www.lieferando.de/en";

  /**
   * Web Elements
   */
  By addressSearchInput = By.cssSelector("#combobox-input_0");
  By addressResultList = By.cssSelector("li[data-qa='location-panel-results-item-element']");

  /**
   * Page Methods
   */

  /**
   * Opens the Lieferando homepage
   *
   * @return HomePage
   */
  public HomePage goToLieferando() {
    Log.info("Opening Lieferando Website.");
    driver.get(baseURL);
    return this;
  }

  /**
   * Verify that current URL is Lieferando HomePage
   *
   * @return
   */
  public HomePage assertHomePage() {
    Log.info("Asserting that current page is Lieferando HomePage.");
    Assert.assertEquals(driver.getCurrentUrl(), baseURL);
    return this;
  }


  /**
   * Enters the address and selects the appropriate option from the result list
   *
   * @param address
   * @return
   */
  public SearchPage enterAddress(String address) {
    Log.info("Entering " + address + " in Address field");
    writeText(addressSearchInput, address);
    selectAnOptionFromList(addressResultList, address);
    return new SearchPage(driver);
  }


}
