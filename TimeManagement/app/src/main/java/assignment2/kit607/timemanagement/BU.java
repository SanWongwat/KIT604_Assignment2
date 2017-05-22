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

    public BU(Context ctx) {
        db = new DBAdapter(ctx);
    }

    //get all unit.
    public List<Unit> GetUnitCode() {
        List<Unit> unitList = new ArrayList<Unit>();
        csr = db.RetrieveUnitCode();
        if (csr != null) {
            if (csr.getCount() == 0) {
                Log.d(TAG, "Database is empty.");
            }
        }

        while (csr.moveToNext()) {
            Unit u = new Unit();
            u.setKey(csr.getInt(csr.getColumnIndex(UnitTable.KEY)));
            u.setUnitId(csr.getString(csr.getColumnIndex(UnitTable.COLUMN_NAME_UNITID)));
            u.setUnitName(csr.getString(csr.getColumnIndex(UnitTable.COLUMN_NAME_UNITNAME)));
            unitList.add(u);
        }
        return unitList;
    }

    public List<Task> RetrieveBriefTaskInfo(boolean complete) {
        Cursor cursor = db.GetBriefTaskInfo(complete);
        if (cursor != null) {
            if (cursor.getCount() == 0) {

                Log.d(TAG, "Database is empty.");
            }
        }
        List<Task> taskList = new ArrayList<Task>();
        while (cursor.moveToNext()) {
            Task task = new Task();
            int index;
            task.setKey(cursor.getInt(cursor.getColumnIndex(TaskTable.KEY)));
            task.setTitle(cursor.getString(cursor.getColumnIndex(TaskTable.COLUMN_NAME_TITLE)));
            task.setDuedate(cursor.getString(cursor.getColumnIndex(TaskTable.COLUMN_NAME_DUEDATE)));
            task.setTime(cursor.getString(cursor.getColumnIndex(TaskTable.COLUMN_NAME_TIME)));
            task.setCompletion(cursor.getString(cursor.getColumnIndex(TaskTable.COLUMN_NAME_COMPLETE)));
            taskList.add(task);
        }
        return taskList;
    }

    public boolean InsertTask(Task t) {
        return db.InsertTask(t);
    }

    //add unit code
    public boolean AddNewUnitCode(Unit u) {
        return db.InsertUnitCode(u);
    }

    //edit unit code
    public boolean EditUnitCode(Unit u) {
        return db.UpdateUnitCode(u);
    }

    //mark task as completed
    public boolean MarkTaskCompleted(Task t) {
        return db.CompleteTask(t);
    }

    public Task GetTaskDetail(int key) {
        Cursor cursor = db.GetTaskDetail(key);
        Task t = new Task();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if (cursor == null || cursor.isAfterLast() || cursor.isBeforeFirst()) {
                return null;
            }
            t.setKey(cursor.getInt(cursor.getColumnIndex(TaskTable.KEY)));
            t.setTitle(cursor.getString(cursor.getColumnIndex(TaskTable.COLUMN_NAME_TITLE)));
            t.setDuedate(cursor.getString(cursor.getColumnIndex(TaskTable.COLUMN_NAME_DUEDATE)));
            t.setTime(cursor.getString(cursor.getColumnIndex(TaskTable.COLUMN_NAME_TIME)));

            Unit u = new Unit();
            Cursor cu = db.RetrieveUnitCode(cursor.getString(cursor.getColumnIndex(TaskTable.COLUMN_NAME_UNITCODE)));
            cu.moveToFirst();
            while (!cu.isAfterLast()) {
                if (cu == null || cu.isAfterLast() || cu.isBeforeFirst()) {
                    return null;
                }
                u.setKey(cu.getInt(cu.getColumnIndex(UnitTable.KEY)));
                u.setUnitId(cu.getString(cu.getColumnIndex(UnitTable.COLUMN_NAME_UNITID)));
                u.setUnitName(cu.getString(cu.getColumnIndex(UnitTable.COLUMN_NAME_UNITNAME)));
                cu.moveToNext();
            }
            t.set_unitCode(u);
            t.setUrgency(cursor.getString(cursor.getColumnIndex(TaskTable.COLUMN_NAME_URGENCY)));
            t.setImportant(cursor.getString(cursor.getColumnIndex(TaskTable.COLUMN_NAME_IMPORTANT)));
            t.setWeight(cursor.getString(cursor.getColumnIndex(TaskTable.COLUMN_NAME_WEIGHT)));
            t.setCompletion(cursor.getString(cursor.getColumnIndex(TaskTable.COLUMN_NAME_COMPLETE)));
            t.setDetail(cursor.getString(cursor.getColumnIndex(TaskTable.COLUMN_NAME_DETAIL)));
            cursor.moveToNext();
        }
        return t;
    }

    public boolean EditTask(Task t) {
        return db.UpdateTask(t);
    }

    public Unit RetrieveUnitCode(int key) {
        Cursor cursor = db.GetUnitCode(key);
        Unit u = new Unit();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if (cursor == null || cursor.isAfterLast() || cursor.isBeforeFirst()) {
                return null;
            }
            u.setKey(cursor.getInt(cursor.getColumnIndex(UnitTable.KEY)));
            u.setUnitId(cursor.getString(cursor.getColumnIndex(UnitTable.COLUMN_NAME_UNITID)));
            u.setUnitName(cursor.getString(cursor.getColumnIndex(UnitTable.COLUMN_NAME_UNITNAME)));
            cursor.moveToNext();
        }
        return u;
    }


    public boolean DeleteTask(int key) {
        return db.DeleteTask(key);
    }

    public boolean DeleteUnitCode(int key) {
        return db.DeleteUnitCode(key);
    }

    public void closeDB(){
        if(db!=null){
            db.close();
            db = null;
        }
        if(csr!=null){
            csr.close();
            csr=null;
        }

    }
}
