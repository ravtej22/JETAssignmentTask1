package com.jet.tests;

import java.util.Collections;

import com.github.dockerjava.core.exec.TopContainerCmdExec;
import com.jet.pages.HomePage;
import com.jet.utils.logs.Log;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
  public WebDriver driver;
  public HomePage homePage;

  public WebDriver getDriver() {
    return driver;
  }

  @BeforeClass
  public void classLevelSetup() {
    Log.info("Tests is starting!");
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--start-maximized");
    driver = new ChromeDriver(options);

  }

  @BeforeMethod
  public void methodLevelSetup() {
    homePage = new HomePage(driver);
  }

  @AfterClass
  public void teardown() {
    Log.info("Tests are ending!");
        driver.quit();
  }
}