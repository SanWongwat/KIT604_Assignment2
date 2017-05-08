package assignment2.kit607.timemanagement;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

/**
 * Created by Allen on 5/5/2017.
 */

public class AddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private BU bu = new BU(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);
        InitialiseAddPageUI();

    }
    public void InitialiseAddPageUI(){
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, Util._low_high_array);
        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        Spinner spn_urgency = (Spinner)findViewById(R.id.add_urgency);
        spn_urgency.setAdapter(dataAdapter);
        spn_urgency.setOnItemSelectedListener(this);

        Spinner spn_important = (Spinner)findViewById(R.id.add_important);
        spn_important.setAdapter(dataAdapter);
        spn_important.setOnItemSelectedListener(this);

        List<Unit> unitList = bu.GetUnitCode();
        int arrSize = unitList.size();
        String[] unitNameArr = new String[arrSize+1];
        for(int i =0;i<arrSize;i++){
            Unit u =  unitList.get(i);
            unitNameArr[i] = u.getUnitId() + ": " + u.getUnitName();
        }
        unitNameArr[arrSize+1] = "Create new unit code.";
        dataAdapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, unitNameArr);
        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        Spinner spn_unit = (Spinner)findViewById(R.id.add_unitcode);
        spn_urgency.setAdapter(dataAdapter);
        spn_urgency.setOnItemSelectedListener(this);


    }
    public void AddTask(View view){
        EditText title = (EditText)findViewById(R.id.add_title);
        Task t = new Task();
        t.setTitle(title.getText().toString());
        Log.d("AddTaskView","Begin add");
        DBAdapter db = new DBAdapter(this);
        db.InsertTask(t);
    }
    public void onItemSelected(AdapterView<?> parent, View v, int position,
                               long id) {
    }
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
