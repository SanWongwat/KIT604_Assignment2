package assignment2.kit607.timemanagement;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Allen on 5/5/2017.
 */

public class EditActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,
        CalendarDialogFragment.NoticeDialogListener,
        TimeDialogFragment.NoticeDialogListener,
        WeightPickerDialogFragment.NoticeDialogListener {
    private final String TAG = "EditActivity";
    private Task mTask;
    private BU bu = new BU(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_task);
        Intent intent = getIntent();
        mTask = (Task) intent.getParcelableExtra(TaskTable.TABLE_NAME);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.btn_toolbar_back) {
                    AlertDialog.Builder bldr = Util.AlertDialogBuilder(EditActivity.this
                            , getString(R.string.confirm_cancel_addEdit_title), getString(R.string.confirm_cancel_addEdit_message));
                    bldr.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            EditActivity.this.finish();
                        }
                    });
                    bldr.create().show();
                } else if (item.getItemId() == R.id.btn_toolbar_delete) {
                    AlertDialog.Builder bldr = Util.AlertDialogBuilder(EditActivity.this
                            , getString(R.string.confirm_delete_task_title), getString(R.string.confirm_delete_task_message));
                    bldr.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            bu = new BU(EditActivity.this);
                            if (bu.DeleteTask(mTask.getKey())) {
                                Toast toast = Toast.makeText(EditActivity.this
                                        , R.string.delete_success, Toast.LENGTH_SHORT);
                                toast.show();
                                finish();
                            }
                        }
                    });
                    bldr.create().show();
                }

                return false;
            }
        });
        InitialisePageUI();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(EditActivity.this);
        builder.setTitle(R.string.confirm_cancel_addEdit_title)
                .setMessage(R.string.confirm_cancel_addEdit_message)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditActivity.this.finish();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //do nothing
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_edit_delete; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_delete, menu);
        return true;
    }

    private void InitialisePageUI() {
        setEditText(R.id.et_edit_title, mTask.getTitle());
        setEditText(R.id.et_edit_duedate, mTask.getDuedate());
        setEditText(R.id.et_edit_time, mTask.getTime());
        setEditText(R.id.et_edit_weight, mTask.getWeight());
        setEditText(R.id.et_edit_detail, mTask.getDetail());
        EditText et_duedate = (EditText) findViewById(R.id.et_edit_duedate);
        et_duedate.setKeyListener(null);
        et_duedate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    DialogFragment df = new CalendarDialogFragment();
                    df.show(getFragmentManager(), "Calendar");
                }
            }
        });
        et_duedate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment df = new CalendarDialogFragment();
                df.show(getFragmentManager(), "Calendar");
            }
        });

        EditText et_time = (EditText) findViewById(R.id.et_edit_time);
        et_time.setKeyListener(null);
        et_time.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    DialogFragment df = new TimeDialogFragment();
                    df.show(getFragmentManager(), "TimePicker");
                }
            }
        });
        et_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment df = new TimeDialogFragment();
                df.show(getFragmentManager(), "TimePicker");
            }
        });

        EditText et_weight = (EditText) findViewById(R.id.et_edit_weight);
        et_weight.setKeyListener(null);
        et_weight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    DialogFragment df = new WeightPickerDialogFragment();
                    df.show(getFragmentManager(), "WeightPicker");
                }
            }
        });
        et_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment df = new WeightPickerDialogFragment();
                df.show(getFragmentManager(), "WeightPicker");
            }
        });

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, Util._low_high_array);
        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        Spinner spn_urgency = (Spinner) findViewById(R.id.spn_edit_urgency);
        spn_urgency.setAdapter(dataAdapter);
        spn_urgency.setOnItemSelectedListener(this);
        spn_urgency.setSelection(dataAdapter.getPosition(mTask.getUrgency()));

        Spinner spn_important = (Spinner) findViewById(R.id.spn_edit_important);
        spn_important.setAdapter(dataAdapter);
        spn_important.setOnItemSelectedListener(this);
        spn_important.setSelection(dataAdapter.getPosition(mTask.getImportant()));

        setUnitCodeSpinner();

        ToggleButton tgb_notify = (ToggleButton) findViewById(R.id.tgb_edit_notify);
        if (mTask.isNotify().equals(getString(R.string.N)))
            tgb_notify.setChecked(false);
        else if (mTask.isNotify().equals(getString(R.string.Y)))
            tgb_notify.setChecked(true);

        CheckBox cb_complete = (CheckBox) findViewById(R.id.cb_edit_complete);
        if (mTask.getCompletion().equals(getString(R.string.N)))
            cb_complete.setChecked(false);
        else if (mTask.getCompletion().equals(getString(R.string.Y)))
            cb_complete.setChecked(true);

    }

    private void setUnitCodeSpinner() {
        List<Unit> unitList = bu.GetUnitCode();
        int arrSize = unitList.size() + 2;
        StringWithTag[] unitNameArr = new StringWithTag[arrSize];
        unitNameArr[0] = new StringWithTag(Util._FirstSpinerText, "");
        unitNameArr[unitNameArr.length - 1] = new StringWithTag(Util._NewUnitcodeText, "");
        for (int i = 1; i < arrSize - 1; i++) {
            Unit u = unitList.get(i - 1);
            unitNameArr[i] = new StringWithTag(u.getUnitId() + ": " + u.getUnitName(), u.getKey());
        }
        ArrayAdapter<StringWithTag> dataAdapter = new ArrayAdapter<StringWithTag>(this,
                R.layout.support_simple_spinner_dropdown_item, unitNameArr);
        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        Spinner spn_unit = (Spinner) findViewById(R.id.spn_edit_unitcode);
        spn_unit.setAdapter(dataAdapter);
        spn_unit.setOnItemSelectedListener(this);
        for (int i = 0; i < unitNameArr.length; i++) {
            String val = unitNameArr[i].getTag().toString();
            if (val != "") {
                if (Integer.parseInt(val) == mTask.get_unitCode().getKey()) {
                    spn_unit.setSelection(i);
                    break;
                }
            }

        }

    }

    private void setEditText(int id, String text) {
        EditText et = (EditText) findViewById(id);
        et.setText(text);
    }

    @Override
    public void onResume() {
        super.onResume();
        setUnitCodeSpinner();
    }

    @Override
    public void onCalendarDialogPositiveClick(DialogFragment dialogFragment) {
        Dialog dialog = dialogFragment.getDialog();
        CalendarView cv = (CalendarView) dialog.findViewById(R.id.dlg_calendar);
        Date selectedDate = new Date(cv.getDate());
        DateFormat df = new SimpleDateFormat(Util._dateFormat);
        String dateStr = df.format(selectedDate);
        EditText et_duedate = (EditText) findViewById(R.id.et_edit_duedate);
        et_duedate.setText(dateStr);
    }

    @Override
    public void onCalendarDialogNegativeClick(DialogFragment dialog) {

    }

    @Override
    public void onTimeDialogPositiveClick(DialogFragment dialogFragment) {
        Dialog dialog = dialogFragment.getDialog();
        TimePicker tp = (TimePicker) dialog.findViewById(R.id.dlg_timepicker);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, tp.getCurrentHour());
        cal.set(Calendar.MINUTE, tp.getCurrentMinute());
        Date date = cal.getTime();
        DateFormat df = new SimpleDateFormat(Util._timeFormat);
        String timeStr = df.format(date);
        EditText et_time = (EditText) findViewById(R.id.et_edit_time);
        et_time.setText(timeStr);
    }

    @Override
    public void onTimeDialogNegativeClick(DialogFragment dialog) {

    }

    @Override
    public void onWeighPickerDialogPositiveClick(DialogFragment dialogFragment) {
        Dialog dialog = dialogFragment.getDialog();
        NumberPicker np = (NumberPicker) dialog.findViewById(R.id.dlg_weightPicker);
        int val = np.getValue();
        EditText et_weight = (EditText) findViewById(R.id.et_edit_weight);
        et_weight.setText(String.valueOf(val));
    }

    @Override
    public void onWeighPickerDialogNegativeClick(DialogFragment dialog) {

    }

    public void EditTask(View view) {
        Task t = new Task();

        t.setKey(mTask.getKey());

        t.setTitle(((EditText) findViewById(R.id.et_edit_title)).getText().toString());

        t.setDuedate(((EditText) findViewById(R.id.et_edit_duedate)).getText().toString());

        t.setTime(((EditText) findViewById(R.id.et_edit_time)).getText().toString());

        StringWithTag uSWT = (StringWithTag) ((Spinner) findViewById(R.id.spn_edit_unitcode))
                .getSelectedItem();
        String uKey = uSWT.getTag().toString();
        Unit u = new Unit();
        u.setKey(Integer.parseInt(uKey));
        t.set_unitCode(u);

        t.setUrgency(((Spinner) findViewById(R.id.spn_edit_urgency))
                .getSelectedItem().toString());

        t.setImportant(((Spinner) findViewById(R.id.spn_edit_important))
                .getSelectedItem().toString());

        t.setWeight(((EditText) findViewById(R.id.et_edit_weight)).getText().toString());

        String notify = getString(R.string.N);
        ToggleButton tgb_notify = (ToggleButton) findViewById(R.id.tgb_edit_notify);
        if (tgb_notify.isChecked()) notify = getString(R.string.Y);
        t.setNotify(notify);

        t.setDetail(((EditText) findViewById(R.id.et_edit_detail)).getText().toString());

        String complete = getString(R.string.N);
        CheckBox cb_complete = (CheckBox) findViewById(R.id.cb_edit_complete);
        if (cb_complete.isChecked()) complete = getString(R.string.Y);
        t.setCompletion(complete);

        if (bu.EditTask(t)) {
            Toast toast = Toast.makeText(this, R.string.edit_success, Toast.LENGTH_SHORT);
            toast.show();

            Intent intent = new Intent();
            intent.putExtra(TaskTable.TABLE_NAME, t);
            t.set_unitCode(bu.RetrieveUnitCode(t.get_unitCode().getKey()));
            setResult(RESULT_OK, intent);
            finish();
        }

    }

    public void onItemSelected(AdapterView<?> parent, View selectedItemView, int position,
                               long id) {
        if (parent.getItemAtPosition(position).toString().equals(Util._NewUnitcodeText)) {
            Spinner s = (Spinner) findViewById(R.id.spn_edit_unitcode);
            if (s.getAdapter().getCount() - 1 == position) {
                //goto add unitcode
                Intent intent = new Intent(this, AddUnitCodeActivity.class);
                startActivityForResult(intent, 0);
            }
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
    }
}
