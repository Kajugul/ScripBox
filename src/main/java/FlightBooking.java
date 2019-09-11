import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FlightBooking extends BaseSelenium
{

    FlightBooking(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void selectOneWay() throws InterruptedException
    {
        By xpath = By.xpath(".//*[@class=\"curPointFlt switchAct\"]");
        click(xpath);
    }

    public void journeyFrom(String from) throws InterruptedException
    {
        from(from);
    }

    public void journeyTo(String to) throws InterruptedException
    {
        to(to);
    }

    /**
     * This method is used to select the source airport
     * @param textToSelect provide the source airport name.
     * @throws InterruptedException
     */
    private void from(String textToSelect) throws InterruptedException
    {
        Thread.sleep(2000);
        By destination = By.id("gosuggest_inputSrc");
        click(destination);
        WebElement autoOptions = driver.findElement(By.xpath(".//*[@id=\"gosuggest_inputSrc\"]"));
        autoOptions.sendKeys(textToSelect);
        Thread.sleep(2000);
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
        Thread.sleep(2000);
    }

    /**
     * This method is used to select the destination airport.
     * @param textToSelect provide the destination airport
     * @throws InterruptedException
     */
    private void to(String textToSelect) throws InterruptedException
    {
        Thread.sleep(2000);
        By src = By.id("gosuggest_inputDest");
        click(src);

        WebElement autoOptions = driver.findElement(src);
        autoOptions.sendKeys(textToSelect);
        Thread.sleep(2000);
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
        Thread.sleep(2000);
    }


    /**
     * This method will select the departure date.
     * @param dateOfMonth date of the month.
     */
    public void selectDepartureDate(String dateOfMonth) throws InterruptedException
    {
        //click on departure
        By departure= By.xpath(".//*[@placeholder=\"Departure\"]");
        click(departure);

        //select date
        List<WebElement> allDates = driver.findElements
                (By.xpath(".//*[@class=\"DayPicker DayPicker--en\"]//div[@class=\"DayPicker-Body\"]//div[@class=\"DayPicker-Week\"]//div[@class=\"DayPicker-Day\"]"));

        for (WebElement ele : allDates)
        {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", ele);
            Thread.sleep(2000);

            String dates = ele.getText().substring(0, 2);
            System.out.println("date: " + dates);
            if (dates.equalsIgnoreCase(dateOfMonth))
            {
                ele.click();
                break;
            }
        }
    }


    /**
     * This method will click on search button.
     */
    public void clickSearch() throws InterruptedException
    {
        By xpath = By.xpath(".//*[@id=\"gi_search_btn\"]");
        click(xpath);
    }


    /**
     * This method will sort the price in decreasing order.
     */
    public void validateResultSet() throws InterruptedException
    {

        WebElement upArrow = driver.findElement(By.xpath(".//*[@class=\"ico13 icon-arrow2-up hpyBlueLt \"]"));
        if (upArrow.isDisplayed())
        {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", upArrow);
            Thread.sleep(2000);
            upArrow.click();
        }

        // validate decreased order

        ArrayList<String> obtainedList = new ArrayList<String>();
        List<WebElement> elementList = driver.findElements(By.xpath(".//*[@class=\"alignItemsEnd flexCol\"]/span[@class=\"alignItemsCenter dF padT2\"]"));

        for (WebElement we : elementList)
        {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", we);
            Thread.sleep(2000);

            obtainedList.add(we.getText());
            js.executeScript("window.scrollBy(0,50)");
        }
        ArrayList<String> sortedList = new ArrayList<String>();
        for (String s : obtainedList)
        {
            sortedList.add(s);
        }
        Collections.sort(sortedList);
        Collections.reverse(sortedList);
        Assert.assertTrue(sortedList.equals(obtainedList));
    }

}
