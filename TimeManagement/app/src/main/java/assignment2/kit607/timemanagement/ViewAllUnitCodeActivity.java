package assignment2.kit607.timemanagement;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen on 5/15/2017.
 */

public class ViewAllUnitCodeActivity extends AppCompatActivity {
    private final String TAG = "ViewAllUnitCodeActivity";
    BU bu;
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
                } else if (item.getItemId() == R.id.btn_toolber_add) {
                    Intent intent = new Intent(ViewAllUnitCodeActivity.this,AddUnitCodeActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });
        PopulateListView();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_edit_delete; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }
    @Override
    public void onResume() {
        super.onResume();
        PopulateListView();
    }

    private void PopulateListView() {
        bu = new BU(this);
        mUnitList = bu.GetUnitCode();
        mUnitListAdapter = new UnitListAdapter();
        ListView taskListView = (ListView) findViewById(R.id.lv_unitCode);
        taskListView.setAdapter(mUnitListAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bu != null) {
            bu.closeDB();
        }
    }

    class UnitListAdapter extends ArrayAdapter<Unit> {
        UnitListAdapter() {
            super(ViewAllUnitCodeActivity.this, android.R.layout.simple_list_item_1, mUnitList);
        }

        public View getView(int position, View row, ViewGroup parent) {

            LayoutInflater inflater = getLayoutInflater();
            row = inflater.inflate(R.layout.unitcode_listrow, null);
            Unit unit = mUnitList.get(position);
            TextView title = (TextView) row.findViewById(R.id.tv_unit);
            title.setText(unit.getUnitId() + ": " + unit.getUnitName());
            final int p = position;
            ImageButton btn_edit = (ImageButton)row.findViewById(R.id.imb_edit);
            btn_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Unit u = mUnitList.get(p);
                    Intent intent = new Intent(ViewAllUnitCodeActivity.this, EditUnitCodeActivity.class);
                    intent.putExtra(UnitTable.TABLE_NAME, u);
                    startActivity(intent);
                }
            });
            ImageButton btn_delete = (ImageButton)row.findViewById(R.id.imb_delete);
            btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder bldr = Util.AlertDialogBuilder(ViewAllUnitCodeActivity.this
                            , getString(R.string.confirm_delete_unitcode_title)
                            , getString(R.string.confirm_delete_unitcode_message));
                    bldr.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            bu = new BU(ViewAllUnitCodeActivity.this);
                            int key = mUnitList.get(p).getKey();
                            if(bu.DeleteUnitCode(key)){
                                Toast toast;
                                toast = Toast.makeText(ViewAllUnitCodeActivity.this, R.string.delete_success, Toast.LENGTH_SHORT);
                                toast.show();
                                PopulateListView();
                            }
                        }
                    });
                    bldr.create().show();

                }
            });
            return row;
        }
    }
}
