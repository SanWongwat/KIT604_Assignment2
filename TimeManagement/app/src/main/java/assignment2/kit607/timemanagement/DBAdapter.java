package assignment2.kit607.timemanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen on 5/6/2017.
 */

public class DBAdapter {
    private final String TAG = "DBAdapter";
    private TMDbHelper dbHelper;
    private SQLiteDatabase sdb;

    public DBAdapter(Context mctx) {
        dbHelper = new TMDbHelper(mctx);
    }

    public boolean InsertTask(Task task) {
        Log.d(TAG, "begin insert");
        sdb = dbHelper.getWritableDatabase();
        long res = -1;
        ContentValues values = new ContentValues();
        values.put(TaskTable.COLUMN_NAME_TITLE, task.getTitle());
        values.put(TaskTable.COLUMN_NAME_DUEDATE, task.getDuedate());
        values.put(TaskTable.COLUMN_NAME_UNITCODE, task.get_unitCode().getUnitId());
        values.put(TaskTable.COLUMN_NAME_URGENCY, task.getUrgency());
        values.put(TaskTable.COLUMN_NAME_IMPORTANT, task.getImportant());
        values.put(TaskTable.COLUMN_NAME_WEIGHT, task.getWeight());
        values.put(TaskTable.COLUMN_NAME_NOTIFY, task.is_notify());
        values.put(TaskTable.COLUMN_NAME_DETAIL, task.getDetail());
        try {
            res = sdb.insert(TaskTable.TABLE_NAME, null, values);
        } catch (Exception e) {
            throw e;
        }
        Log.d(TAG,String.valueOf(res));
        if (res != -1) return true;
        else return false;
    }

    public List<Task> GetTask() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        //String query = "SELECT t.*,u.Unitname  FROM Task t INNER JOIN UnitCode u ON t.UnitCode = u.UnitId";
        String query = "SELECT * FROM " + TaskTable.TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            if (cursor.getCount() == 0) {

                Log.d(TAG, "Database is empty.");
            }
        }
        List<Task> taskList = new ArrayList<Task>();
        Log.d(TAG, "Begin retreiving data.");
        while (cursor.moveToNext()) {
            Task task = new Task();
            int index;
            index = cursor.getColumnIndex(TaskTable.COLUMN_NAME_TITLE);
            task.setTitle(cursor.getString(index));
            Log.d(TAG, cursor.getString(index));
//            index = cursor.getColumnIndex(TaskTable.COLUMN_NAME_DUEDATE);
//            task.setDuedate(cursor.getString(index));
//            index = cursor.getColumnIndex(TaskTable.COLUMN_NAME_UNITCODE);
//            String unitCode = cursor.getString(index);
//            index = cursor.getColumnIndex(UnitTable.COLUMN_NAME_UNITNAME);
//            String unitName = cursor.getString(index);
//            Unit unit = new Unit();
//            unit.set_unitCode(unitCode);
//            unit.setUnitName(unitName);
//            task.set_unitCode(unit);
//            index = cursor.getColumnIndex(TaskTable.COLUMN_NAME_URGENCY);
//            task.setUrgency(cursor.getString(index));
//            index = cursor.getColumnIndex(TaskTable.COLUMN_NAME_IMPORTANT);
//            task.setImportant(cursor.getString(index));
//            index = cursor.getColumnIndex(TaskTable.COLUMN_NAME_WEIGHT);
//            task.setWeight(cursor.getString(index));
//            index = cursor.getColumnIndex(TaskTable.COLUMN_NAME_NOTIFY);
//            task.setNotify(cursor.getString(index));
//            index = cursor.getColumnIndex(TaskTable.COLUMN_NAME_DETAIL);
//            task.setDetail(cursor.getString(index));
            taskList.add(task);
        }
        return taskList;
    }

    public ArrayList<Task> GetTask(String key) {
        ArrayList<Task> taskList = new ArrayList<Task>();

        return taskList;
    }


    public Cursor RetrieveUnitCode() {
        sdb = dbHelper.getReadableDatabase();
        String[] projection = {
                UnitTable.COLUMN_NAME_UNITID,
                UnitTable.COLUMN_NAME_UNITNAME
        };

        Cursor c = sdb.query(UnitTable.TABLE_NAME, projection, null, null, null, null, null);

        return c;

    }

    public boolean InsertUnitCode(Unit u) {
        sdb = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UnitTable.COLUMN_NAME_UNITID, u.getUnitId());
        values.put(UnitTable.COLUMN_NAME_UNITNAME, u.getUnitName());
        long res = -1;
        try {
            res = sdb.insert(UnitTable.TABLE_NAME, null, values);
        } catch (Exception e) {
            throw e;
        }
        if (res != -1) {
            return true;
        } else {
            return false;
        }
    }

    //update unit code
    public boolean UpdateUnitCode(Unit u) {
        sdb = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UnitTable.COLUMN_NAME_UNITNAME, u.getUnitName());
        String selection = UnitTable.COLUMN_NAME_UNITID + " = ?";
        String[] SelectionArgs = {u.getUnitId()};
        long res = -1;
        try {
            res = sdb.update(UnitTable.TABLE_NAME, values, selection, SelectionArgs);
        } catch (Exception e) {
            throw e;
        }
        if (res != -1) {
            return true;
        } else {
            return false;
        }
    }

    //delete unitcode
    public boolean DeleteUnitCode(Unit u) {
        sdb = dbHelper.getWritableDatabase();
        String selection = UnitTable.COLUMN_NAME_UNITID + " = ?";
        String[] SelectionArgs = {u.getUnitId()};
        long res = -1;
        try {
            res = sdb.delete(UnitTable.TABLE_NAME, selection, SelectionArgs);
        } catch (Exception e) {
            throw e;
        }
        if (res != -1) {
            return true;
        } else {
            return false;
        }
    }

    private class TMDbHelper extends SQLiteOpenHelper {
        public static final String TAG = "TMDbHelper";
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "TimeManagement.db";
        //sql statement for creating task table
        private static final String SQL_CREATE_TASKENTRIES = "CREATE TABLE "
                + TaskTable.TABLE_NAME
                + " (" + TaskTable.Key + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TaskTable.COLUMN_NAME_TITLE + " STRING, "
                + TaskTable.COLUMN_NAME_DUEDATE + " STRING, "
                + TaskTable.COLUMN_NAME_UNITCODE + " STRING, "
                + TaskTable.COLUMN_NAME_URGENCY + " STRING, "
                + TaskTable.COLUMN_NAME_IMPORTANT + " STRING, "
                + TaskTable.COLUMN_NAME_WEIGHT + " STRING, "
                + TaskTable.COLUMN_NAME_NOTIFY + " STRING, "
                + TaskTable.COLUMN_NAME_DETAIL + " STRING);";
        //sql statement for creating unitcode table
        private static final String SQL_CREATE_UNITCODEENTRIES = "CREATE TABLE "
                + UnitTable.TABLE_NAME
                + " (" + UnitTable.Key + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + UnitTable.COLUMN_NAME_UNITID + " STRING, "
                + UnitTable.COLUMN_NAME_UNITNAME + " STRING);";

        private static final String SQL_CREATE_TMQENTRIES = "CREATE TABLE "
                + TMQTable.TABLE_NAME
                + " (" + TMQTable.Key + " INTEGER PRIMARY KEY, "
                + TMQTable.COLUMN_NAME_CATEGORY + " STRING, "
                + TMQTable.COLUMN_NAME_QUESTION + " STRING, "
                + TMQTable.COLUMN_NAME_SCORE + " STRING);";
        private static final String SQL_DROP_TASK = "DROP TABLE IF EXIST " + TaskTable.TABLE_NAME + ";";
        private static final String SQL_DROP_UNITCODE = "DROP TABLE IF EXIST " + UnitTable.TABLE_NAME + ";";
        private static final String SQL_DROP_TMQ = "DROP TABLE IF EXIST " + TMQTable.TABLE_NAME + ";";

        public TMDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            Log.d(TAG, "DatabaseHelper onCreate");
            db.execSQL(SQL_CREATE_TASKENTRIES);
            db.execSQL(SQL_CREATE_UNITCODEENTRIES);
            db.execSQL(SQL_CREATE_TMQENTRIES);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
//        db.execSQL(SQL_DELETE_ENTRIES);
//        onCreate(db);
        }

        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //onUpgrade(db, oldVersion, newVersion);
        }
    }
}
