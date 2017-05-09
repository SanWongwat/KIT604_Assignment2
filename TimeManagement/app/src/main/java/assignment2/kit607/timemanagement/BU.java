package assignment2.kit607.timemanagement;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen on 5/8/2017.
 */

public class BU {
    private final String TAG = "BU";
    private DBAdapter db;
    private Cursor csr;
    public BU(Context ctx){
        db = new DBAdapter(ctx);
    }

    //get all unit.
    public List<Unit> GetUnitCode() {
        List<Unit> unitList = new ArrayList<Unit>();
        csr = db.RetrieveUnitCode();
        if(csr!=null){
            if(csr.getCount()==0){
                    Log.d(TAG, "Database is empty.");
            }
        }

        while(csr.moveToNext()){
            Unit u = new Unit();
            u.setUnitId(csr.getString(csr.getColumnIndex(UnitTable.COLUMN_NAME_UNITID)));
            u.setUnitName(csr.getString(csr.getColumnIndex(UnitTable.COLUMN_NAME_UNITNAME)));
            unitList.add(u);
        }
        return unitList;
    }

    public boolean InsertTask(Task t) {
        return db.InsertTask(t);
    }

    //add unit code
    public boolean AddNewUnitCode(Unit u) {
        return db.InsertUnitCode(u);
    }

    //edit unit code
    public boolean EditUnitCode(Unit u){
        return db.UpdateUnitCode(u);
    }

}
