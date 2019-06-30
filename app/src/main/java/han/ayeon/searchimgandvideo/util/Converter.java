package han.ayeon.searchimgandvideo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Converter {

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS", Locale.KOREA);

    public static Date StringToDate(String dateTime) {

        if(dateTime!=null) {
            String time = dateTime.replace("T", " ");
            Date date = null;
            try {
                date = formatter.parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return date;
        } else {
            return null;
        }


    }
}
