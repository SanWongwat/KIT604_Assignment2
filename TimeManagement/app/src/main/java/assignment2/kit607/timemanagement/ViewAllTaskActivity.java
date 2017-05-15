package assignment2.kit607.timemanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen on 5/5/2017.
 */

public class ViewAllTaskActivity extends AppCompatActivity {

    private final String TAG = "ViewAllTaskActivity";
    private final int tag_key = 0;
    List<Task> mTaskList = new ArrayList<Task>();
    TaskListAdapter mTaskListAdapter = null;
    BU bu = new BU(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_all_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.btn_toolbar_back) {
                    ViewAllTaskActivity.this.finish();
                }else if (item.getItemId() == R.id.btn_toolber_add) {
                    Intent intent = new Intent(ViewAllTaskActivity.this,AddActivity.class);
                    startActivity(intent);
                }

                return false;
            }
        });
        PopulateListView();

    }

    @Override
    public void onResume(){
        super.onResume();
        PopulateListView();
    }

    private void PopulateListView() {
        BU bu = new BU(this);
        mTaskList = bu.RetrieveBriefTaskInfo();
        mTaskListAdapter = new TaskListAdapter();
        ListView taskListView = (ListView) findViewById(R.id.lw_TaskList);
        taskListView.setAdapter(mTaskListAdapter);
        taskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Task t = mTaskList.get(position);
                Intent intent = new Intent(ViewAllTaskActivity.this, ViewTaskDetailActivity.class);
                intent.putExtra(TaskTable.KEY, t.getKey());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_edit_delete; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    class TaskListAdapter extends ArrayAdapter<Task> {
        TaskListAdapter() {
            super(ViewAllTaskActivity.this, android.R.layout.simple_list_item_1, mTaskList);
        }

        public View getView(int position, View row, ViewGroup parent) {

            LayoutInflater inflater = getLayoutInflater();
            row = inflater.inflate(R.layout.task_listrow, null);
            Task t = mTaskList.get(position);
            TextView title = (TextView) row.findViewById(R.id.tv_viewrow_title);
            title.setText(t.getTitle());

            TextView duedate = (TextView) row.findViewById(R.id.tv_viewrow_duedate);
            duedate.setText(t.getDuedate());

            TextView time = (TextView) row.findViewById(R.id.tv_viewrow_time);
            time.setText(t.getTime());

            TextView complete = (TextView) row.findViewById(R.id.tv_viewrow_completion);
            if (t.getCompletion().equals(getString(R.string.N))) {
                complete.setText(getString(R.string.incomplete));
            } else {
                complete.setText(getString(R.string.complete));
            }
            return row;
        }
    }
}
