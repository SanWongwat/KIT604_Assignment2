package assignment2.kit607.timemanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Task> mTaskList = new ArrayList<Task>();
    TaskListAdapter mTaskListAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void NewTask(View view) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }

    protected void GetTask(View view) {
        mTaskList = RetrieveTaskList();
        mTaskListAdapter = new TaskListAdapter();
        ListView taskListView = (ListView) findViewById(R.id.TaskListView);
        taskListView.setAdapter(mTaskListAdapter);
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
            TextView title = (TextView) row.findViewById(R.id.taskTitle);
            TextView duedate = (TextView) row.findViewById(R.id.taskDuedate);
            title.setText(t.getTitle());
            duedate.setText(t.getDuedate());

            return row;
        }
    }
}
