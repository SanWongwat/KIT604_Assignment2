package assignment2.kit607.timemanagement;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;

/**
 * Created by Allen on 5/9/2017.
 */

public class WeightPickerDialogFragment extends DialogFragment {

    public interface NoticeDialogListener {
        public void onWeighPickerDialogPositiveClick(DialogFragment dialog);

        public void onWeighPickerDialogNegativeClick(DialogFragment dialog);
    }

    NoticeDialogListener mListener;
    private final String TAG = "CalendarDialogFragment";
    private final int min = 1;
    private final int max = 100;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        RelativeLayout rl = (RelativeLayout) inflater.inflate(R.layout.dialog_weightpicker, null);
        NumberPicker np = (NumberPicker)rl.findViewById(R.id.dlg_weightPicker);
        np.setMinValue(min);
        np.setMaxValue(max);
        builder.setMessage(R.string.dlg_weightpicker);
        builder.setView(rl)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onWeighPickerDialogPositiveClick(WeightPickerDialogFragment.this);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onWeighPickerDialogNegativeClick(WeightPickerDialogFragment.this);
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
            mListener = (NoticeDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }
}
