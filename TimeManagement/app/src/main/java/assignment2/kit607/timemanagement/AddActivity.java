package assignment2.kit607.timemanagement;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
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

public class AddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,
        CalendarDialogFragment.NoticeDialogListener,
        TimeDialogFragment.NoticeDialogListener,
        WeightPickerDialogFragment.NoticeDialogListener {

    private String TAG = "AddActivity";
    private BU bu = new BU(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.btn_toolbar_back) {
                    AlertDialog.Builder bldr = Util.AlertDialogBuilder(AddActivity.this
                            , getString(R.string.confirm_cancel_addEdit_title), getString(R.string.confirm_cancel_addEdit_message));
                    bldr.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            AddActivity.this.finish();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_edit_delete; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder bldr = Util.AlertDialogBuilder(AddActivity.this
                , getString(R.string.confirm_cancel_addEdit_title), getString(R.string.confirm_cancel_addEdit_message));
        bldr.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                AddActivity.this.finish();
            }
        });
        bldr.create().show();
    }

    @Override
    public void onCalendarDialogPositiveClick(DialogFragment dialogFragment) {
        Dialog dialog = dialogFragment.getDialog();
        CalendarView cv = (CalendarView) dialog.findViewById(R.id.dlg_calendar);
        Date selectedDate = new Date(cv.getDate());
        DateFormat df = new SimpleDateFormat(Util._dateFormat);
        String dateStr = df.format(selectedDate);
        EditText et_duedate = (EditText) findViewById(R.id.et_add_duedate);
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
        EditText et_time = (EditText) findViewById(R.id.et_add_time);
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
        EditText et_weight = (EditText) findViewById(R.id.et_add_weight);
        et_weight.setText(String.valueOf(val));
    }

    @Override
    public void onWeighPickerDialogNegativeClick(DialogFragment dialog) {

    }

    @Override
    public void onResume() {
        super.onResume();
        setUnitCodeSpinner();
    }

    //initialise page
    private void InitialisePageUI() {

        EditText et_duedate = (EditText) findViewById(R.id.et_add_duedate);
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

        EditText et_time = (EditText) findViewById(R.id.et_add_time);
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

        EditText et_weight = (EditText) findViewById(R.id.et_add_weight);
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

        Spinner spn_urgency = (Spinner) findViewById(R.id.spn_add_urgency);
        spn_urgency.setAdapter(dataAdapter);
        spn_urgency.setOnItemSelectedListener(this);

        Spinner spn_important = (Spinner) findViewById(R.id.spn_add_important);
        spn_important.setAdapter(dataAdapter);
        spn_important.setOnItemSelectedListener(this);

        setUnitCodeSpinner();
    }

    //add task
    public void AddTask(View view) {
        String title= ((EditText) findViewById(R.id.et_add_title)).getText().toString();
        String duedate = ((EditText) findViewById(R.id.et_add_duedate)).getText().toString();
        String empty = "";
        StringWithTag uSWT = (StringWithTag) ((Spinner) findViewById(R.id.spn_add_unitcode))
                .getSelectedItem();
        if(title.equalsIgnoreCase(empty) ){
            AlertDialog.Builder bldr = Util.AlertDialogBuilder(this
                    , getString(R.string.validate_input), getString(R.string.validate_title));
            bldr.create().show();
            return;
        }
        if(duedate.equalsIgnoreCase(empty)){
            AlertDialog.Builder bldr = Util.AlertDialogBuilder(this
                    , getString(R.string.validate_input), getString(R.string.validate_duedate));
            bldr.create().show();
            return;
        }
        if(uSWT.getTag().toString().equalsIgnoreCase(empty)){
            AlertDialog.Builder bldr = Util.AlertDialogBuilder(this
                    , getString(R.string.validate_input), getString(R.string.validate_unitcode));
            bldr.create().show();
            return;
        }
        Task t = new Task();

        t.setTitle(title);

        t.setDuedate(duedate);

        t.setTime(((EditText) findViewById(R.id.et_add_time)).getText().toString());

        String uKey = uSWT.getTag().toString();
        Unit u = new Unit();
        Log.d(TAG, uKey);
        u.setKey(Integer.parseInt(uKey));
        t.set_unitCode(u);

        t.setUrgency(((Spinner) findViewById(R.id.spn_add_urgency))
                .getSelectedItem().toString());

        t.setImportant(((Spinner) findViewById(R.id.spn_add_important))
                .getSelectedItem().toString());

        t.setWeight(((EditText) findViewById(R.id.et_add_weight)).getText().toString());

        t.setDetail(((EditText) findViewById(R.id.et_add_detail)).getText().toString());

        t.setCompletion(getString(R.string.N));
        Log.d("AddTaskView", "Begin add");

        Toast toast;
        if (bu.InsertTask(t)) {
            toast = Toast.makeText(this, R.string.add_task_success, Toast.LENGTH_SHORT);
            toast.show();
            finish();
        }

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
        Spinner spn_unit = (Spinner) findViewById(R.id.spn_add_unitcode);
        spn_unit.setAdapter(dataAdapter);
        spn_unit.setOnItemSelectedListener(this);

    }

    public void onItemSelected(AdapterView<?> parent, View selectedItemView, int position,
                               long id) {
        if (parent.getItemAtPosition(position).toString().equals(Util._NewUnitcodeText)) {
            Spinner s = (Spinner) findViewById(R.id.spn_add_unitcode);
            if (s.getAdapter().getCount() - 1 == position) {
                //goto add unitcode
                Intent intent = new Intent(this, AddUnitCodeActivity.class);
                startActivityForResult(intent, 0);
            }
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bu != null) {
            bu.closeDB();
        }
    }
}
