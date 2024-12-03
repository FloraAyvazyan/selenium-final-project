package ge.tbc.tesautomation;

import ge.tbc.data.Constants;
import ge.tbc.util.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class HolidayPageTests extends BaseTest {

    @Test
    public void descendingOrderTest() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText(Constants.REST_LINK))).click();
        Util.clickWithJs(js,  wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.SORT_BY_RELEVANCE))));
        Util.clickWithJs(js, wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.SORT_BY_DESCENDING_PRICE))));

        List<Double> allPrices = new ArrayList<>();


        boolean hasNextPage = true;
        while (hasNextPage) {
            Thread.sleep(3000);

            List<WebElement> priceElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(Constants.PRICE_XPATH)));
            for (WebElement priceElement : priceElements) {
                allPrices.add(Util.parsePrice(priceElement.getText()));
            }
            hasNextPage = Util.clickNextArrow(driver, wait, Constants.NEXT_ARROW_XPATH);
        }

        double firstPrice = allPrices.getFirst();
        double maxPrice = Collections.max(allPrices);
        System.out.println("max price:" + maxPrice);
        System.out.println("first price:" + firstPrice);
        Assert.assertEquals(firstPrice, maxPrice, "The first offer is not the most expensive one.");
    }


    @Test
    public void ascendingOrderTest() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText(Constants.REST_LINK))).click();
        Util.clickWithJs(js, wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.SORT_BY_RELEVANCE))));
        Util.clickWithJs(js, wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.SORT_BY_ASCENDING_PRICE))));

        List<Double> allPrices = new ArrayList<>();
        boolean hasNextPage = true;
        while (hasNextPage) {
            Thread.sleep(3000);

            List<WebElement> priceElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(Constants.PRICE_XPATH)));
            for (WebElement priceElement : priceElements) {
                allPrices.add(Util.parsePrice(priceElement.getText()));
            }
            hasNextPage = Util.clickNextArrow(driver, wait, Constants.NEXT_ARROW_XPATH);
        }

        double firstPrice = allPrices.getFirst();
        double minPrice = Collections.min(allPrices);
        System.out.println("max price:" + minPrice);
        System.out.println("first price:" + firstPrice);
        Assert.assertEquals(firstPrice, minPrice, "The first offer is not the last expensive one.");
    }


    @Test
    public void filterTest() throws InterruptedException {
        WebElement restLikButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(Constants.REST_LINK)));
        Util.clickWithJs(js, restLikButton);

        WebElement mountainResortsButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.MOUNTAIN_RESORTS)));
        Util.clickWithJs(js, mountainResortsButton);

        Util.clickWithJs(js, wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.PAYMENT_TYPE_RADIO))));

        WebElement aboveListBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.FULL_PAYMENT_BUTTON)));
        Assert.assertTrue(aboveListBtn.isDisplayed());

        WebElement sortByRelevanceButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.SORT_BY_RELEVANCE)));
        Util.clickWithJs(js, sortByRelevanceButton);

        WebElement sortByAscendingButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.SORT_BY_ASCENDING_PRICE)));
        Util.clickWithJs(js, sortByAscendingButton);


        List<Double> prices = new ArrayList<>();
        boolean hasNextPage = true;
        while (hasNextPage) {
            Thread.sleep(3000);
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(Constants.PRICE_XPATH)));
            List<WebElement> priceElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(Constants.PRICE_XPATH)));
            for (WebElement priceElement : priceElements) {
                prices.add(Util.parsePrice(priceElement.getText()));
            }
            hasNextPage = Util.clickNextArrow(driver, wait, Constants.NEXT_ARROW_XPATH);
        }


        double firstPrice = prices.getFirst();
        double minPrice = Collections.min(prices);
        System.out.println("max price:" + minPrice);
        System.out.println("first price:" + firstPrice);
        Assert.assertEquals(firstPrice, minPrice, "The first offer is not the least expensive one.");
    }


    @Test
    public void priceRangeTest() throws InterruptedException {
        WebElement restLikButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(Constants.REST_LINK)));
        Util.clickWithJs(js, restLikButton);

        String minP = "300";
        String maxP = "700";

        List<WebElement> inputs = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(Constants.INPUTS_XPATH)));
        inputs.getFirst().sendKeys(minP);
        inputs.getLast().sendKeys(maxP);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.ENTER_BUTTON))).click();

        boolean hasNextPage = true;
        while (hasNextPage) {
            Thread.sleep(3000);
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(Constants.PRICE_XPATH)));
            List<WebElement> priceElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(Constants.PRICE_XPATH)));
            List<Double> prices = new ArrayList<>();

            for (WebElement priceElement : priceElements) {
                System.out.println("gettext: " + priceElement.getText());
                String priceText = priceElement.getText().replace("₾", "").replace(",", "").trim();
                double price = Double.parseDouble(priceText);
                prices.add(price);
                Assert.assertTrue(price >= Double.parseDouble(minP) && price <= Double.parseDouble(maxP),
                        "Offer price " + price + " is outside the specified range (" + minP + " - " + maxP + ")");
            }
            System.out.println("Prices on this page: " + prices);
            Util.isSortedAscending(prices);
            hasNextPage = Util.clickNextArrow(driver, wait, Constants.NEXT_ARROW_XPATH);
        }


    }
}



