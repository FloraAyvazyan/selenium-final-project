package ge.tbc.util;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
    public static boolean isSortedDescending(List<Double> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) > list.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static double parsePrice(String priceText) {
        return Double.parseDouble(priceText.replace("₾", "").replace(",", "").trim());
    }

    public static boolean isSortedAscending(List<Double> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) < list.get(i - 1)) {
                return false;
            }
        }
        return true;
    }
    public static boolean clickNextArrow(WebDriver driver, WebDriverWait wait, String nextArrowXPath) {
        List<WebElement> nextArrowElements = driver.findElements(By.xpath(nextArrowXPath));
        if (!nextArrowElements.isEmpty()) {
            WebElement nextArrow = nextArrowElements.get(0);
            if (nextArrow.isEnabled() && !nextArrow.getAttribute("class").contains("opacity-50")) {
                nextArrow.click();
                return true;
            }
        }
        return false;
    }
    public static void clickWithJs(JavascriptExecutor js, WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }

    public static String parseDateTime(String text) {
        String regex = "(\\d{1,2})(?:\\s+[a-zA-Z]*)?,?\\s*(\\d{1,2}:\\d{2})";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            String day = matcher.group(1);
            String time = matcher.group(2);

            return "Day: " + day + ", Time: " + time;
        } else {
            return "No match found!";
        }
    }
}