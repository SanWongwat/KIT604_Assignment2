package assignment2.kit607.timemanagement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Allen on 5/6/2017.
 */

public class Util {
    public static final String _dateTimeFormat="dd/mm/yyyy HH:mm";
    public static final String _dateFormat="EEEE d MMM yyyy";
    public static final String _timeFormat="hh:mm a";
    public static final String[] _low_high_array = new String[]{"Low", "High"};
    public static final String _NewUnitcodeText = "Create new unit code.";
    public static final String _FirstSpinerText = "--Select Unitcode--";

    public static Date StringToDate(String strDate)throws ParseException{
        SimpleDateFormat f = new SimpleDateFormat(_dateFormat);
        return f.parse(strDate);
    }
}
