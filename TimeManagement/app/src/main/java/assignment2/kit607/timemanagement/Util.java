package assignment2.kit607.timemanagement;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by Allen on 5/6/2017.
 */

class Util {
    public static final String _dateTimeFormat = "dd/mm/yyyy HH:mm";
    public static final String _dateFormat = "EEEE d MMM yyyy";
    public static final String _timeFormat = "hh:mm a";
    public static final String[] _low_high_array = new String[]{"Low", "High"};
    public static final String _NewUnitcodeText = "Create new unit code.";
    public static final String _FirstSpinerText = "--Select Unitcode--";
    public static final int _requestCode = 0;

    public static AlertDialog.Builder AlertDialogBuilder(Context ctx,String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
       final Activity a = (Activity)ctx;
        builder.setTitle(title)
                .setMessage(message)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //do nothing
                    }
                });
        return builder;
    }

}

class StringWithTag {
    private String string;
    private Object tag;

    public StringWithTag(String stringPart, Object tagPart) {
        string = stringPart;
        tag = tagPart;
    }

    @Override
    public String toString() {
        return string;
    }

    public Object getTag() {
        return tag;
    }
}