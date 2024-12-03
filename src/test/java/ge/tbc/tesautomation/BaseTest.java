package ge.tbc.tesautomation;

import ge.tbc.data.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;


public class BaseTest {
    public WebDriver driver;
    public WebDriverWait wait;
    public JavascriptExecutor js;
    Actions actions;

    @BeforeClass
    @Parameters("browserType")
    public void setup(@Optional("chrome")String browserType) throws Exception {
        if (browserType.equalsIgnoreCase("chrome")) {

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

        } else if (browserType.equalsIgnoreCase("edge")) {

            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();

        } else if (browserType.equalsIgnoreCase("fireFox")) {

            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();

        }
        else {
            throw new Exception("Invalid browser name: " + browserType);
        }
        driver.get(Constants.BASE_URL);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        js = (JavascriptExecutor) driver;
        actions = new Actions(driver);
    }

    @AfterClass
    public void tearDown() {
        if (!(driver == null)) {
            driver.quit();
        }
    }
}