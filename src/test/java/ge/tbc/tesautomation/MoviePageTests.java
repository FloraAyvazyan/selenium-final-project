package ge.tbc.tesautomation;

import ge.tbc.data.Constants;
import ge.tbc.util.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class MoviePageTests extends BaseTest {

    @Test
    public void testMovieSelection() throws InterruptedException {
        driver.findElement(By.xpath(Constants.VETANXMEBI_XPATH)).click();
        WebElement kinoLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(Constants.KINO)));
        kinoLink.click();

        List<WebElement> movies = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(Constants.ALL_MOVIEA)));


        boolean foundEastPoint = false;
        int movieIndex = 0;
        while (!foundEastPoint && movieIndex < movies.size()) {
            Thread.sleep(3000);
            movies = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(Constants.ALL_MOVIEA)));
            movies.get(movieIndex).click();
            try {
                WebElement eastPoint = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Constants.EASTPOINTBTN_XPATH)));
                if (eastPoint.isEnabled()) {
                    foundEastPoint = true;
                }
            } catch (TimeoutException e) {
                driver.navigate().back();
                movieIndex++;
            }
        }
        if (!foundEastPoint) {
            System.out.println("EastPoint option not found for any movie.");
        }

        String movieName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(Constants.MOVIE_TAGNAME))).getText();

        WebElement caveaEastPointWrapper = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Constants.CAVEAEASTPOINT_WRAPPER)));
        js.executeScript("arguments[0].scrollIntoView(true);", caveaEastPointWrapper);

        //კინოს სახელის წამოღება
        String cinema = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Constants.CAVEAEASTPOINT_WRAPPER))).getText();


        List<WebElement> options = caveaEastPointWrapper.findElements(By.xpath(Constants.OPTIONS_XPATH));
        //თარიღის წამოღება
        String dateTime = options.getLast().findElement(By.cssSelector(".items-end")).getText();

        Util.clickWithJs(js, options.getLast()); //ბოლო ოფშენზე დაკლიკვა

        //მონაცემების წამოღება პოპაპის შემდეგ
        String popupMovieName =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='dialog']//h2[@weight='bold']"))).getText();
        String popUpCinema = driver.findElement(By.xpath("//div[@role='dialog']//h2[@weight='bold']/following-sibling::div//p[1]")).getText();
        String popupDateTime = driver.findElement(By.xpath("//div[@role='dialog']//h2[@weight='bold']/following-sibling::div//p[2]")).getText();

        Assert.assertEquals(movieName, popupMovieName);
        Assert.assertEquals(cinema, popUpCinema);
        Assert.assertEquals(Util.parseDateTime(dateTime), Util.parseDateTime(popupDateTime));

        WebElement chartColor = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Constants.CHART_COLOR)));
        WebElement freeSetColor = driver.findElement(By.cssSelector(Constants.FREESET_SELECTOR));
        Assert.assertEquals(chartColor.getCssValue("color"), freeSetColor.getCssValue("color"), "Colors arent the same");

        List<WebElement> vacantPlaces = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(Constants.VACANT_PLACE)));
        Util.clickWithJs(js, vacantPlaces.getFirst());

        WebElement registration = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(Constants.REGISTRATION)));
        js.executeScript("arguments[0].scrollIntoView(true);", registration);
        Util.clickWithJs(js, registration);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder=\"ელფოსტა*\"]"))).sendKeys("wrong email");
        driver.findElement(By.cssSelector("#password")).sendKeys("123456.Aa");
        driver.findElement(By.cssSelector("#PasswordRetype")).sendKeys("123456.Aa");
        driver.findElement(By.xpath("//span[text() = 'მდედრობითი']")).click();
        driver.findElement(By.cssSelector("#name")).sendKeys("Flora");
        driver.findElement(By.cssSelector("#surname")).sendKeys("Ayvazyan");
        WebElement birth_year = driver.findElement(By.cssSelector(".select2-selection__arrow"));
        js.executeScript("arguments[0].scrollIntoView(true);", birth_year);
        birth_year.click();
        driver.findElement(By.xpath("//li[text() = '2003']")).click();
        driver.findElement(By.cssSelector("#Phone")).sendKeys("551521324");
        driver.findElement(By.xpath("//input[@placeholder=\"SMS კოდი\"]")).sendKeys("1234");

        WebElement firstCheckBox = driver.findElement(By.xpath("//input[@id='test' and @type='checkbox']/following-sibling::span[@class='checkmark']"));
        Util.clickWithJs(js, firstCheckBox);

        WebElement secondCheckBox = driver.findElement(By.xpath("//input[@id='tbcAgreement' and @type='checkbox']/following-sibling::span[@class='checkmark']"));
        Util.clickWithJs(js, secondCheckBox);

        WebElement errorInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#input-error-email")));
        Assert.assertTrue(errorInput.isDisplayed(), "Error message doesn't appeare");
    }
}



