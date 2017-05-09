package assignment2.kit607.timemanagement;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;

import java.util.Date;

/**
 * Created by Allen on 5/8/2017.
 */

public class AddUnitCodeActivity extends AppCompatActivity{

    private final String TAG = "AddUnitCodeActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_unitcode);

    }



    protected void AddUnitCode(View view){
        String id = ((EditText)findViewById(R.id.um_add_unitid)).getText().toString();
        String name = ((EditText)findViewById(R.id.um_add_unitname)).getText().toString();
        Unit u = new Unit();
        u.setUnitId(id);
        u.setUnitName(name);
        BU bu = new BU(this);
        boolean isSuccess;
        isSuccess = bu.AddNewUnitCode(u);
        if(isSuccess){
            // notify success
        }
    }
}
