package com.jet.tests;

import static com.jet.utils.extentreports.ExtentTestManager.startTest;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

public class RestaurantCategory extends BaseTest {
  @Test(priority = 0, description = "Verify the category filter for Restaurants search")
  public void selectRestaurantCategoryTest(Method method) throws Exception {
    startTest(method.getName(), "Verify the category filter for Restaurants search");

    homePage
            .goToLieferando()
            .assertHomePage()
            .enterAddress("Gropiusstadt, Berlin")
            .assertResultPage()
            .selectRestaurantCategory("Italian")
            .goToLieferando()
            .assertHomePage();
  }
}