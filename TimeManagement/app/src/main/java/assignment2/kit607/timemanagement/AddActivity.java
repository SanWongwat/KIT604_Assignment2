package assignment2.kit607.timemanagement;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

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
        TimeDialogFragment.NoticeDialogListener {

    private String TAG = "AddActivity";
    private BU bu = new BU(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);
        InitialiseAddPageUI();

    }

    @Override
    public void onCalendarDialogPositiveClick(DialogFragment dialog) {
        Dialog d = dialog.getDialog();
        CalendarView cv = (CalendarView) d.findViewById(R.id.dlg_calendar);
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
    public void onTimeDialogPositiveClick(DialogFragment dialog) {
        Dialog d = dialog.getDialog();
        TimePicker tp = (TimePicker) d.findViewById(R.id.dlg_timepicker);
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

    public void InitialiseAddPageUI() {

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
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, Util._low_high_array);
        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        Log.d(TAG, "init urgency");
        Spinner spn_urgency = (Spinner) findViewById(R.id.spn_add_urgency);
        spn_urgency.setAdapter(dataAdapter);
        spn_urgency.setOnItemSelectedListener(this);

        Log.d(TAG, "init important");
        Spinner spn_important = (Spinner) findViewById(R.id.spn_add_important);
        spn_important.setAdapter(dataAdapter);
        spn_important.setOnItemSelectedListener(this);

        Log.d(TAG, "init unit");
        List<Unit> unitList = bu.GetUnitCode();
        int arrSize = unitList.size() + 2;
        String[] unitNameArr = new String[arrSize];
        unitNameArr[0] = Util._FirstSpinerText;
        unitNameArr[unitNameArr.length - 1] = Util._NewUnitcodeText;
        for (int i = 1; i < arrSize - 1; i++) {
            Log.d(TAG, i + "");
            Unit u = unitList.get(i - 1);
            unitNameArr[i] = u.getUnitId() + ": " + u.getUnitName();
            Log.d(TAG, unitNameArr[i]);
        }
        dataAdapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, unitNameArr);
        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        Spinner spn_unit = (Spinner) findViewById(R.id.spn_add_unitcode);
        spn_unit.setAdapter(dataAdapter);
        spn_unit.setOnItemSelectedListener(this);
    }

    public void AddTask(View view) {
        EditText title = (EditText) findViewById(R.id.et_add_title);
        Task t = new Task();
        t.setTitle(title.getText().toString());
        EditText duedate = (EditText) findViewById(R.id.et_add_duedate);
        EditText time = (EditText) findViewById(R.id.et_add_time);
        String datetime = duedate.getText() + " " + time.getText();
        t.setDuedate(datetime);
        String[] unitArr = ((Spinner) findViewById(R.id.spn_add_unitcode))
                .getSelectedItem().toString().split(":");
        Unit u = new Unit();
        u.setUnitId(unitArr[0].trim());
        u.setUnitName(unitArr[1].trim());
        t.set_unitCode(u);
        t.setUrgency(((Spinner) findViewById(R.id.spn_add_urgency))
                .getSelectedItem().toString());
        t.setImportant(((Spinner) findViewById(R.id.spn_add_important))
                .getSelectedItem().toString());
        Log.d("AddTaskView", "Begin add");

        DBAdapter db = new DBAdapter(this);
        db.InsertTask(t);

    }

    public void onItemSelected(AdapterView<?> parent, View selectedItemView, int position,
                               long id) {
        Log.d(TAG, parent.getItemAtPosition(position).toString());
        Log.d(TAG, R.id.spn_add_unitcode + "");
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
}
