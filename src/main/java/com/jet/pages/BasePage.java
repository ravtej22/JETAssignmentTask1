package com.jet.pages;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
  public WebDriver driver;
  public WebDriverWait wait;

  //Constructor
  public BasePage(WebDriver driver) {
    this.driver = driver;
    wait = new WebDriverWait(driver, Duration.ofSeconds(30));
  }

  //Click Method
  public void click(By by) {
    waitForClickable(by).click();
  }

  //Write Text
  public void writeText(By by, String text) {
    waitVisibility(by).sendKeys(text);
  }

  //Read Text
  public String readText(By by) {
    return waitVisibility(by).getText();
  }

  public List<WebElement> findList(By locator) {
    return driver.findElements(locator);
  }

  //Wait
  public WebElement waitVisibility(By by) {
    return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
  }

  public WebElement waitForClickable(By by) {
    return wait.until(ExpectedConditions.elementToBeClickable(by));
  }

  public void scrollToElement() throws Exception {
    ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    Thread.sleep(1000);
    wait.until(webDriver -> "complete".equals(((JavascriptExecutor) webDriver).executeScript("return document.readyState")));
  }


  public void selectAnOptionFromList(By locator, String option) {

    waitVisibility(locator);
    List<WebElement> webElementList = driver.findElements(locator);

    for (WebElement webElement : webElementList) {
      if (webElement.getText().equals(option))
        webElement.click();
    }
  }
}