package assignment2.kit607.timemanagement;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TimePicker;

/**
 * Created by Allen on 5/9/2017.
 */

public class TimeDialogFragment extends DialogFragment {
    public interface NoticeDialogListener {
        public void onTimeDialogPositiveClick(DialogFragment dialog);
        public void onTimeDialogNegativeClick(DialogFragment dialog);
    }

    NoticeDialogListener mListener;

    private final String TAG = "CalendarDialogFragment";
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        LinearLayout ll = (LinearLayout)inflater.inflate(R.layout.dialog_time,null);
        TimePicker tp = (TimePicker)ll.findViewById(R.id.dlg_timepicker);

        builder.setMessage(R.string.dlg_timepicker_title);
        builder.setView(ll).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int id) {
                mListener.onTimeDialogPositiveClick(TimeDialogFragment.this);
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int id) {
                mListener.onTimeDialogNegativeClick(TimeDialogFragment.this);
            }
        });
        return builder.create();
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (TimeDialogFragment.NoticeDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }
}
