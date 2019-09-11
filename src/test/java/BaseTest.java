import Utility.Constant;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class BaseTest
{
    public WebDriver driver = null;

    @BeforeTest
    public void beforeTest()
    {
        System.out.println("Initialing the browser for you....Please wait...");
        String chromeDriverPath = System.getProperty("user.dir") + "//Datafiles//chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(40000, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(Constant.URL);
    }

    @AfterTest
    public void afterTest() throws InterruptedException
    {
        Thread.sleep(3000);
        System.out.println("Closing the browser....");
        driver.close();
    }
}
