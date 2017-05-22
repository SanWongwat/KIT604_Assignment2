package assignment2.kit607.timemanagement;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Allen on 5/8/2017.
 */

public class AddUnitCodeActivity extends AppCompatActivity {

    private final String TAG = "AddUnitCodeActivity";
    BU bu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_unitcode);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.btn_toolbar_back) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddUnitCodeActivity.this);
                    builder.setTitle(R.string.confirm_cancel_addEdit_title)
                            .setMessage(R.string.confirm_cancel_addEdit_message)
                            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    AddUnitCodeActivity.this.finish();
                                }
                            })
                            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //do nothing
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_edit_delete; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    protected void AddUnitCode(View view) {
        String id = ((EditText) findViewById(R.id.um_add_unitid)).getText().toString();
        String name = ((EditText) findViewById(R.id.um_add_unitname)).getText().toString();
        Unit u = new Unit();
        u.setUnitId(id);
        u.setUnitName(name);
        bu = new BU(this);
        if (bu.AddNewUnitCode(u)) {
            Toast toast;
            toast = Toast.makeText(this, R.string.add_code_success, Toast.LENGTH_SHORT);
            toast.show();
            finish();

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bu != null) {
            bu.closeDB();
        }
    }
}
