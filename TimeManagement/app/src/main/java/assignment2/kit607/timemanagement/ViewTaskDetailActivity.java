package assignment2.kit607.timemanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by Allen on 5/10/2017.
 */

public class ViewTaskDetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Task task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_task_detail);
        Intent pIntent = getIntent();
        int key = pIntent.getIntExtra(TaskTable.KEY,0);
        BU bu = new BU(this);
        task = bu.GetTaskDetail(key);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.miBack) {
                }
                if (item.getItemId() == R.id.miDelete) {
                }

                return false;
            }
        });
        InitialisePageUI();

    }
    private void InitialisePageUI() {
        setTextView(R.id.tv_detail_title, task.getTitle());
        setTextView(R.id.tv_detail_duedate, task.getDuedate());
        setTextView(R.id.tv_detail_time, task.getTitle());

        Unit u = task.get_unitCode();
        setTextView(R.id.tv_detail_unitcode, u.getUnitId() + ": " +u.getUnitName());
        setTextView(R.id.tv_detail_urgency, task.getUrgency());
        setTextView(R.id.tv_detail_important, task.getImportant());
        setTextView(R.id.tv_detail_weight, task.getWeight());
        setTextView(R.id.tv_detail_notify, task.isNotify());
        setTextView(R.id.tv_detail_detail, task.getDetail());

    }

    private void setTextView(int id, String text) {
        TextView tv = (TextView)findViewById(id);
        tv.setText(text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}
