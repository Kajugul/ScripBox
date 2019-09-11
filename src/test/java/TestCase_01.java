import Utility.Constant;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase_01 extends BaseTest
{
    private String expectedTitle = "Online flight booking, Hotels, Bus &amp; Holiday Packages at Goibibo";
    private FlightBooking flightBooking = null;

    @Test(priority = 1, description = "This is Test Case will insure the Title of Goibibo site.")
    public void verifyTitle()
    {
        Assert.assertEquals(driver.getTitle(), expectedTitle);
    }

    @Test(priority = 2, description = "This test case will search the flights.")
    public void selectJourneyDetails() throws InterruptedException
    {

        flightBooking = new FlightBooking(driver);
        flightBooking.selectOneWay();
        flightBooking.journeyFrom(Constant.FromLocation);
        flightBooking.journeyTo(Constant.toLocation);
        flightBooking.selectDepartureDate(Constant.getTomorrowDate());
    }

    @Test(priority = 3, description = "This test case will validate the price in decrease in order.")
    public void verifyResultOrderInDesendingOrder() throws InterruptedException
    {
        flightBooking = new FlightBooking(driver);
        flightBooking.clickSearch();
        flightBooking.validateResultSet();
    }
}
