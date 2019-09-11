package Utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Constant
{
    public static String FromLocation = "Bangalore (BLR)";
    public static String toLocation = "Mumbai (BOM)";
    public static String URL = "https://www.goibibo.com/";

    /**
     * This method return the tommorow's date
     * @return
     */
    public static String getTomorrowDate()
    {
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd");
        return formatter.format(dt);
    }
}
