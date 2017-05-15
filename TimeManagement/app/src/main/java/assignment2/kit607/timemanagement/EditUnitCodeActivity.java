package assignment2.kit607.timemanagement;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Allen on 5/15/2017.
 */

public class EditUnitCodeActivity extends AppCompatActivity {

    private final String TAG = "EditUnitCodeActivity";
    private Unit mUnit;
    private BU bu = new BU(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_unitcode);
        Intent intent = getIntent();
        mUnit = (Unit) intent.getParcelableExtra(UnitTable.TABLE_NAME);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.btn_toolbar_back) {
                    AlertDialog.Builder bldr = Util.AlertDialogBuilder(EditUnitCodeActivity.this
                            , getString(R.string.confirm_cancel_addEdit_title), getString(R.string.confirm_cancel_addEdit_message));
                    bldr.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            EditUnitCodeActivity.this.finish();
                        }
                    });
                    bldr.create().show();
                } else if (item.getItemId() == R.id.btn_toolbar_delete) {
                    AlertDialog.Builder bldr = Util.AlertDialogBuilder(EditUnitCodeActivity.this
                            , getString(R.string.confirm_delete_unitcode_title)
                            , getString(R.string.confirm_delete_unitcode_message));
                    bldr.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            bu = new BU(EditUnitCodeActivity.this);
                            if (bu.DeleteUnitCode(mUnit.getKey())) {
                                Toast toast = Toast.makeText(EditUnitCodeActivity.this
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
        InitialisePageUI();
    }

    private void InitialisePageUI() {
        setEditText(R.id.et_edit_unitId, mUnit.getUnitId());
        setEditText(R.id.et_edit_unitName, mUnit.getUnitName());
        Button bt_confirm = (Button)findViewById(R.id.btn_edit_uc_confirm);
        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Unit u = new Unit();
                u.setKey(mUnit.getKey());
                u.setUnitId(((EditText)findViewById(R.id.et_edit_unitId)).getText().toString());
                u.setUnitName(((EditText)findViewById(R.id.et_edit_unitName)).getText().toString());

                if(bu.EditUnitCode(u)){
                    Toast toast = Toast.makeText(EditUnitCodeActivity.this, R.string.edit_success, Toast.LENGTH_SHORT);
                    toast.show();
                    finish();
                }
            }
        });
    }
    private void setEditText(int id, String text) {
        EditText et = (EditText) findViewById(id);
        et.setText(text);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_edit_delete; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_delete, menu);
        return true;
    }
}
