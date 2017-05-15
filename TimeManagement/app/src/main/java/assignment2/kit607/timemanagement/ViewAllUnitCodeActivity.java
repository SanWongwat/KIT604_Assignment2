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
 * Created by Allen on 5/15/2017.
 */

public class ViewAllUnitCodeActivity extends AppCompatActivity {
    private final String TAG = "ViewAllUnitCodeActivity";
    private final int tag_key = 0;
    List<Unit> mUnitList = new ArrayList<Unit>();
    UnitListAdapter mUnitListAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_all_unitcode);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.btn_toolbar_back) {
                    ViewAllUnitCodeActivity.this.finish();
                }
                return false;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void PopulateListView() {
        BU bu = new BU(this);
        mUnitList = bu.GetUnitCode();
        mUnitListAdapter = new UnitListAdapter();
        ListView taskListView = (ListView) findViewById(R.id.lv_unitCode);
        taskListView.setAdapter(mUnitListAdapter);
        taskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Unit u = mUnitList.get(position);
                Intent intent = new Intent(ViewAllUnitCodeActivity.this, EditUnitCodeActivity.class);
                intent.putExtra(TaskTable.KEY, u);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_edit_delete; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    class UnitListAdapter extends ArrayAdapter<Unit> {
        UnitListAdapter() {
            super(ViewAllUnitCodeActivity.this, android.R.layout.simple_list_item_1, mUnitList);
        }

        public View getView(int position, View row, ViewGroup parent) {

            LayoutInflater inflater = getLayoutInflater();
            row = inflater.inflate(R.layout.task_listrow, null);
            Unit unit = mUnitList.get(position);
            TextView title = (TextView) row.findViewById(R.id.tv_unit);
            title.setText(unit.getUnitId() + ": " + unit.getUnitName());

            return row;
        }
    }
}
