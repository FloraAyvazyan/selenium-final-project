package ge.tbc.tesautomation;

import ge.tbc.data.Constants;
import ge.tbc.util.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LandingPageTests extends BaseTest{
    @Test
    public void activeCategoryTest() throws InterruptedException {
        WebElement categoriesButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.CATEGORIES_XPATH)));
        Util.clickWithJs(js, categoriesButton);

        WebElement sportCategory = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Constants.SPORT_BTN)));
        actions.moveToElement(sportCategory).perform();

        WebElement restLikButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.CARTING_BTN)));
        Util.clickWithJs(js, restLikButton);

        Thread.sleep(3000);

        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, Constants.EXPECTEDURL, "URL did not change as expected.");

    }


    @Test
    public void logoTest() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText(Constants.REST_LINK))).click();

        WebElement swoopLogo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.SWOOPLOGO_XPATH)));
        Util.clickWithJs(js, swoopLogo);

        Thread.sleep(3000);
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, Constants.EXPECTEDURL2, "The logo did not redirect to the landing page!");


    }
}
