package assignment2.kit607.timemanagement;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.CalendarView;
import android.widget.LinearLayout;

import java.util.Date;

/**
 * Created by Allen on 5/8/2017.
 */

public class CalendarDialogFragment extends DialogFragment{

    public interface NoticeDialogListener {
        public void onCalendarDialogPositiveClick(DialogFragment dialog);
        public void onCalendarDialogNegativeClick(DialogFragment dialog);
    }

    NoticeDialogListener mListener;


    private final String TAG = "CalendarDialogFragment";
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        LinearLayout ll = (LinearLayout)inflater.inflate(R.layout.dialog_calendar,null);
        CalendarView cv = (CalendarView)ll.findViewById(R.id.dlg_calendar);
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year,
                                            int month, int dayOfMonth) {
                Date selectedDate = new Date(year, month, dayOfMonth);
                Log.d(TAG,selectedDate.toString());
            }
        });
        builder.setMessage(R.string.dlg_calendar_title);
        builder.setView(ll)
                .setPositiveButton(R.string.ok,new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int id){
                        mListener.onCalendarDialogPositiveClick(CalendarDialogFragment.this);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onCalendarDialogNegativeClick(CalendarDialogFragment.this);
                    }
                }
                );
        return builder.create();
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (NoticeDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

}
