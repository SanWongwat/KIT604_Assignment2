package assignment2.kit607.timemanagement;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements CalendarDialogFragment.NoticeDialogListener {

    private final String TAG = "MainActivity";
    List<Task> mTaskList = new ArrayList<Task>();
    TaskListAdapter mTaskListAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initiateMainActivity();

    }
    private void initiateMainActivity(){
        Button btn_newTask = (Button)findViewById(R.id.main_activity_button_new_task);
        btn_newTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
        Button btn_viewAllTask = (Button)findViewById(R.id.main_activity_button_view_task);
        btn_viewAllTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewAllTaskActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void GetTask(View view) {
        mTaskList = RetrieveTaskList();
        mTaskListAdapter = new TaskListAdapter();
//        ListView taskListView = (ListView) findViewById(R.id.TaskListView);
//        taskListView.setAdapter(mTaskListAdapter);
    }

    protected void AddUnitCode(View view) {
        Intent intent = new Intent(this, AddUnitCodeActivity.class);
        startActivity(intent);
    }

    protected void ShowDialog(View view){
        DialogFragment df = new CalendarDialogFragment();
        df.show(getFragmentManager(),"Calendar");
    }
    @Override
    public void onCalendarDialogPositiveClick(DialogFragment dialog) {
        Dialog d = dialog.getDialog();
        CalendarView cv = (CalendarView)d.findViewById(R.id.dlg_calendar);
        Log.d(TAG, new Date(cv.getDate()).toString());
    }
    @Override
    public void onCalendarDialogNegativeClick(DialogFragment dialog) {

    }

    public List<Task> RetrieveTaskList() {
        List<Task> taskList = new ArrayList<Task>();
        DBAdapter db = new DBAdapter(this);
        taskList = db.GetTask();
        return taskList;
    }

    class TaskListAdapter extends ArrayAdapter<Task> {
        TaskListAdapter() {
            super(MainActivity.this, android.R.layout.simple_list_item_1, mTaskList);
        }

        public View getView(int position, View row, ViewGroup parent) {

            LayoutInflater inflater = getLayoutInflater();
            row = inflater.inflate(R.layout.task_listrow, null);
            Task t = mTaskList.get(position);
            TextView title = (TextView) row.findViewById(R.id.tv_viewrow_title);
            TextView duedate = (TextView) row.findViewById(R.id.tv_viewrow_duedate);
            title.setText(t.getTitle());
            duedate.setText(t.getDuedate());

            return row;
        }
    }
}
