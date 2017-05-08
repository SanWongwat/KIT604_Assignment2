package assignment2.kit607.timemanagement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Allen on 5/6/2017.
 */

public class Util {
    public static final String _dateFormat="dd/mmm/yyyy HH:mm:ss";
    public static final String[] _low_high_array = new String[]{"Low", "High"};

    public static Date StringToDate(String strDate)throws ParseException{
        SimpleDateFormat f = new SimpleDateFormat(_dateFormat);
        return f.parse(strDate);
    }
}
