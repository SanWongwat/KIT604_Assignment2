package assignment2.kit607.timemanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button new_task,view_task,tmq,setting;
    private TextView top1_task,top2_task,top3_task,top4_task,top5_task; // create objects for TextView in main  screen.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set a Click Listener for Button New_Task
        new_task=(Button)findViewById(R.id.main_activity_button_new_task);
        new_task.setOnClickListener(new View.OnClickListener(){
            public void onClick(View c){
                Intent i = new Intent(MainActivity.this,AddActivity.class);
                startActivity(i);
            }
        });

        //let TextView in main Screen be clickable and link to Task_Detail Activity
            //use Array to manage all TextView in Main Screen
        TextView[] ViewGroup = {top1_task=(TextView)findViewById(R.id.top_1_task),
        top2_task=(TextView)findViewById(R.id.top_2_task),
        top3_task=(TextView)findViewById(R.id.top_3_task),
        top4_task=(TextView)findViewById(R.id.top_4_task),
        top5_task=(TextView)findViewById(R.id.top_5_task),};
        for(TextView task:ViewGroup) {
            task.setClickable(true);
            task.setFocusable(true);
            task.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // set intent to connecting to Task_Detail Activity
                    //......
                }
            });
        }
    }
}
