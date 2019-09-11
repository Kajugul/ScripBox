import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BaseSelenium
{
    public WebDriver driver;

    BaseSelenium(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void click(By locater) throws InterruptedException
    {
        highlighting(locater);
        Thread.sleep(2000);
        driver.findElement(locater).click();
        Thread.sleep(1000);
    }

    private void highlighting(By locators) throws InterruptedException
    {
        WebElement ele = driver.findElement(locators);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", ele);
        Thread.sleep(2000);
    }

}
