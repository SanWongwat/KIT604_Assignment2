package assignment2.kit607.timemanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by Allen on 5/5/2017.
 */

public class AddActivity extends AppCompatActivity {

    private Spinner spin_hour, spin_unit_code,spin_urgency,spin_importance,spin_weight;
    private ArrayList<String> unit_code= new ArrayList<String>();
    private ArrayList<String> urgency= new ArrayList<String>();
    private ArrayList<String> importance= new ArrayList<String>();
    private ArrayList<String> weight = new ArrayList<String>();
    private Button add_task_cancel;
    private ImageButton add_task_image_button;
    private EditText add_task_edit_due_date;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);
        //Create DropList for Hour Created by Tom 7/5/2017
         spin_hour = (Spinner)findViewById(R.id.add_task_spinner_time);

       ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddActivity.this,
                R.layout.spinner,getResources().getStringArray(R.array.hour));
        adapter.setDropDownViewResource(R.layout.drop_down_list_view);
        spin_hour.setAdapter(adapter);

        //create droplist for unit code
        spin_unit_code=(Spinner)findViewById(R.id.add_task_unit_code);
        ArrayAdapter adapter_unit_code = new ArrayAdapter(this,R.layout.spinner,getUnitCode());
        adapter_unit_code.setDropDownViewResource(R.layout.drop_down_list_view);
        spin_unit_code.setAdapter(adapter_unit_code);

        // Create drop list for urgency

        spin_urgency=(Spinner)findViewById(R.id.add_task_urgency);
        ArrayAdapter<String> adapter_urgency= new ArrayAdapter<String>(this, R.layout.spinner,getUrgency());
        adapter_urgency.setDropDownViewResource(R.layout.drop_down_list_view);
        spin_urgency.setAdapter(adapter_urgency);

        // Create drop list for importance
        spin_importance=(Spinner)findViewById(R.id.add_task_importance);
        ArrayAdapter<String> adapter_importance= new ArrayAdapter<String>(this, R.layout.spinner,getImportance());
        adapter_importance.setDropDownViewResource(R.layout.drop_down_list_view);
        spin_importance.setAdapter(adapter_importance);

        // Create drop list for weight
        spin_weight=(Spinner)findViewById(R.id.add_task_weight);
        ArrayAdapter<String> adapter_weight= new ArrayAdapter<String>(this, R.layout.spinner,getWeight());
        adapter_weight.setDropDownViewResource(R.layout.drop_down_list_view);
        spin_weight.setAdapter(adapter_weight);

        //activate cancel button
        add_task_cancel=(Button)findViewById(R.id.add_task_cancel);
        add_task_cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(AddActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        // Create intent for add_task_image_button to connect to calendar activity
        add_task_image_button=(ImageButton)findViewById(R.id.add_task_image_button);
        add_task_image_button.setOnClickListener(new View.OnClickListener(){
            @Override
                public void onClick(View v){
                Intent i = new Intent(AddActivity.this, calendarActivity.class);
                startActivity(i);
            }

        });


        //Set date to EditText

      if(this.getIntent().getExtras().getString("activity")=="calendarActivity"){
            setDate();
        }

    }


    // deliver date to Edit text from calendar Activity
    public void setDate(){
        add_task_edit_due_date=(EditText)findViewById(R.id.add_task_edit_due_date);
        Intent i=getIntent();
        Bundle date= i.getBundleExtra("date");
        int year=date.getInt("year");
        int month=date.getInt("month");
        int day=date.getInt("day");
        add_task_edit_due_date.setText(day+"/"+month+"/"+year);


    }
    //Create DropList for Unit Code Created by Tom 7/5/2017
    private ArrayList<String> getUnitCode(){
        unit_code.add("KIT607-Mobile\bApplication\bDevelopment");
        return unit_code;
    }
    private ArrayList<String> getUrgency(){
        urgency.add("Low");
        urgency.add("Medium");
        urgency.add("High");
        return urgency;
    }

    private ArrayList<String> getImportance(){
       importance.add("Low");
        importance.add("Medium");
        importance.add("High");
        return importance;
    }
    private ArrayList<String> getWeight(){
        weight.add("");
        return weight;
    }
}
