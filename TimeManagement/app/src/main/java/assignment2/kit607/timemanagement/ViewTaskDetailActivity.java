package assignment2.kit607.timemanagement;

import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Allen on 5/10/2017.
 */

public class ViewTaskDetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Task mTask;
    private final String TAG = "ViewTaskDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_task_detail);
        Intent pIntent = getIntent();
        int key = pIntent.getIntExtra(TaskTable.KEY, 0);
        BU bu = new BU(this);
        mTask = bu.GetTaskDetail(key);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.btn_toolbar_back) {
                    ViewTaskDetailActivity.this.finish();
                } else if (item.getItemId() == R.id.btn_toolbar_edit) {
                    Intent intent = new Intent(ViewTaskDetailActivity.this, EditActivity.class);
                    intent.putExtra(TaskTable.TABLE_NAME, mTask);
                    startActivityForResult(intent, Util._requestCode);
                } else if (item.getItemId() == R.id.btn_toolbar_delete) {
                    AlertDialog.Builder bldr = Util.AlertDialogBuilder(ViewTaskDetailActivity.this
                            , getString(R.string.confirm_delete_task_title), getString(R.string.confirm_delete_task_message));
                    bldr.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            BU bu = new BU(ViewTaskDetailActivity.this);
                                    if(bu.DeleteTask(mTask.getKey())){
                                        Toast toast = Toast.makeText(ViewTaskDetailActivity.this
                                                , R.string.delete_success, Toast.LENGTH_SHORT);
                                        toast.show();
                                        finish();
                                    }
                        }
                    });
                    bldr.create().show();
                }

                return false;
            }
        });
        InitialisePageUI(mTask);

    }

    private void InitialisePageUI(Task t) {
        setTextView(R.id.tv_detail_title, t.getTitle());
        setTextView(R.id.tv_detail_duedate, t.getDuedate());
        setTextView(R.id.tv_detail_time, t.getTime());

        Unit u = t.get_unitCode();
        setTextView(R.id.tv_detail_unitcode, u.getUnitId() + ": " + u.getUnitName());

        String urgent = t.getUrgency();
        String important = t.getImportant();
        String category = "";
        if(urgent.equals(getString(R.string.low))){
            if(important.equals(getString(R.string.low))){
                category = getString(R.string.isnot_urgent)+" & "+getString(R.string.isnot_important);
            } else{
                category = getString(R.string.isnot_urgent)+" & "+getString(R.string.is_important);
            }
        } else{
            if(important.equals(getString(R.string.low))){
                category = getString(R.string.is_urgent)+" & "+getString(R.string.isnot_important);
            } else{
                category = getString(R.string.is_urgent)+" & "+getString(R.string.is_important);
            }
        }

        setTextView(R.id.tv_detail_category, category);
        setTextView(R.id.tv_detail_weight, t.getWeight());

        String mNotify = t.isNotify();
        String notification = "";
        if (mNotify.equals(getString(R.string.N))) notification = getString(R.string.no);
        else if (mNotify.equals(getString(R.string.Y))) notification = getString(R.string.yes);
        setTextView(R.id.tv_detail_notify, notification);

        setTextView(R.id.tv_detail_detail, t.getDetail());

        String mComplete = t.getCompletion();
        String completion = "";
        if (mComplete.equals(getString(R.string.N))) completion = getString(R.string.no);
        else if (mComplete.equals(getString(R.string.Y))) completion = getString(R.string.yes);
        setTextView(R.id.tv_detail_complete, completion);

    }

    private void setTextView(int id, String text) {
        TextView tv = (TextView) findViewById(id);
        tv.setText(text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_edit_delete; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_delete, menu);
        return true;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Util._requestCode) {
            if (resultCode == RESULT_OK) {
                Task t = data.getParcelableExtra(TaskTable.TABLE_NAME);
                mTask = t;
                InitialisePageUI(t);
            }
        }
    }
}
