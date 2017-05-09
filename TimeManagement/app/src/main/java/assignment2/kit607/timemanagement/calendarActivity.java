package assignment2.kit607.timemanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

public class calendarActivity extends AppCompatActivity {
    private Button calendar_cancel, calendar_confirm;
    private CalendarView calendarView;
    private int year, month, day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        //roll back to add_task activity when clicking cancel button
        calendar_cancel=(Button)findViewById(R.id.calendar_cancel);
        calendar_cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(calendarActivity.this, AddActivity.class);
                startActivity(i);
            }
        });

        // get chose date

          calendarView=(CalendarView)findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            @Override
            public void onSelectedDayChange(CalendarView v,
                                            int y,
                                            int m,
                                            int d){
                year=y;
                month=m;
                day=d;   //load selected date to each variable.
            }
        });

        // create Button Listener to put date into EXtra which then delivered to AddActivity

        calendar_confirm=(Button)findViewById(R.id.calendar_confirm);
        calendar_confirm.setOnClickListener(new View.OnClickListener(){
            @Override
                public void onClick(View v){

                Intent i = new Intent(calendarActivity.this, AddActivity.class);
                Bundle date = new Bundle();
                date.putString("activity","calendarActivity");
                date.putInt("year",year);
                date.putInt("month",month);
                date.putInt("day",day);
                i.putExtras(date);
                startActivity(i);

            }
        });


    }
}
