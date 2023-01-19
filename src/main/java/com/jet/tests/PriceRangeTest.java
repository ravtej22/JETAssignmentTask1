package com.jet.tests;

import static com.jet.utils.extentreports.ExtentTestManager.startTest;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

public class PriceRangeTest extends BaseTest {
  @Test(priority = 0, description = "Verify the Price range filter for restaurants search")
  public void priceRangeTest(Method method) throws Exception {
    startTest(method.getName(), "Verify the Price range filter for restaurants search");

    homePage
            .goToLieferando()
            .assertHomePage()
            .enterAddress("Gropiusstadt, Berlin")
            .assertResultPage()
            .selectPriceRange(1000)
            .verifyRestaurantsPriceRange(1000)
            .goToLieferando()
            .assertHomePage();
  }

}