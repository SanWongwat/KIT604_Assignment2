package assignment2.kit607.timemanagement;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

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
        values.put(TaskTable.COLUMN_NAME_TIME, task.getTime());
        values.put(TaskTable.COLUMN_NAME_UNITCODE, task.get_unitCode().getKey());
        values.put(TaskTable.COLUMN_NAME_URGENCY, task.getUrgency());
        values.put(TaskTable.COLUMN_NAME_IMPORTANT, task.getImportant());
        values.put(TaskTable.COLUMN_NAME_WEIGHT, task.getWeight());
        values.put(TaskTable.COLUMN_NAME_NOTIFY, task.isNotify());
        values.put(TaskTable.COLUMN_NAME_DETAIL, task.getDetail());
        values.put(TaskTable.COLUMN_NAME_COMPLETE, task.getCompletion());
        try {
            res = sdb.insert(TaskTable.TABLE_NAME, null, values);
        } catch (Exception e) {
            throw e;
        }
        if (res != -1) return true;
        else return false;
    }


    public Cursor GetBriefTaskInfo() {
        sdb = dbHelper.getReadableDatabase();
        String[] projection = {
                TaskTable.KEY,
                TaskTable.COLUMN_NAME_TITLE,
                TaskTable.COLUMN_NAME_DUEDATE,
                TaskTable.COLUMN_NAME_TIME,
                TaskTable.COLUMN_NAME_COMPLETE
        };
        return sdb.query(TaskTable.TABLE_NAME, projection, null, null, null, null, null);


    }

    public ArrayList<Task> GetTask(String key) {
        ArrayList<Task> taskList = new ArrayList<Task>();

        return taskList;
    }


    public Cursor RetrieveUnitCode() {
        sdb = dbHelper.getReadableDatabase();

        Cursor c = sdb.query(UnitTable.TABLE_NAME, null, null, null, null, null, null);

        return c;

    }

    public Cursor RetrieveUnitCode(String key) {
        sdb = dbHelper.getReadableDatabase();

        String selection = UnitTable.KEY + " = ?";
        String[] selectionArgs = {key};
        Cursor c = sdb.query(UnitTable.TABLE_NAME, null, selection, selectionArgs, null, null, null);
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
        values.put(UnitTable.COLUMN_NAME_UNITID, u.getUnitId());
        values.put(UnitTable.COLUMN_NAME_UNITNAME, u.getUnitName());
        String selection = UnitTable.KEY + " = ?";
        String[] SelectionArgs = {String.valueOf(u.getKey())};
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
    public boolean DeleteUnitCode(int key) {
        sdb = dbHelper.getWritableDatabase();
        String selection = UnitTable.KEY + " = ?";
        String[] SelectionArgs = {String.valueOf(key)};
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

    public boolean CompleteTask(Task t) {
        sdb = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TaskTable.COLUMN_NAME_COMPLETE, Resources.getSystem().getString(R.string.Y));
        String selection = TaskTable.KEY + " = ?";
        String[] SelectionArgs = {String.valueOf(t.getKey())};
        long res = -1;
        try {
            res = sdb.update(TaskTable.TABLE_NAME, values, selection, SelectionArgs);
        } catch (Exception e) {
            throw e;
        }
        if (res != -1) {
            return true;
        } else {
            return false;
        }
    }

    public Cursor GetTaskDetail(int key) {
        sdb = dbHelper.getReadableDatabase();
        String selection = TaskTable.KEY + " = ?";
        String[] selectionArgs = {String.valueOf(key)};
        Cursor c = sdb.query(TaskTable.TABLE_NAME, null, selection, selectionArgs, null, null, null);
        return c;
    }

    public boolean UpdateTask(Task t) {
        Log.d(TAG, "begin update");
        sdb = dbHelper.getWritableDatabase();
        long res = -1;
        ContentValues values = new ContentValues();
        values.put(TaskTable.COLUMN_NAME_TITLE, t.getTitle());
        values.put(TaskTable.COLUMN_NAME_DUEDATE, t.getDuedate());
        values.put(TaskTable.COLUMN_NAME_TIME, t.getTime());
        values.put(TaskTable.COLUMN_NAME_UNITCODE, t.get_unitCode().getKey());
        values.put(TaskTable.COLUMN_NAME_URGENCY, t.getUrgency());
        values.put(TaskTable.COLUMN_NAME_IMPORTANT, t.getImportant());
        values.put(TaskTable.COLUMN_NAME_WEIGHT, t.getWeight());
        values.put(TaskTable.COLUMN_NAME_NOTIFY, t.isNotify());
        values.put(TaskTable.COLUMN_NAME_DETAIL, t.getDetail());
        values.put(TaskTable.COLUMN_NAME_COMPLETE, t.getCompletion());
        String selection = TaskTable.KEY + " = ?";
        String[] selectionArgs = {String.valueOf(t.getKey())};
        try {
            res = sdb.update(TaskTable.TABLE_NAME, values, selection, selectionArgs);
        } catch (Exception e) {
            throw e;
        }
        if (res != -1) return true;
        else return false;
    }

    public Cursor GetUnitCode(int key) {
        sdb = dbHelper.getReadableDatabase();
        String selection = UnitTable.KEY + " = ?";
        String[] selectionArgs = {String.valueOf(key)};
        Cursor c = sdb.query(UnitTable.TABLE_NAME, null, selection, selectionArgs, null, null, null);
        return c;
    }

    public boolean DeleteTask(int key) {
        sdb = dbHelper.getWritableDatabase();
        long res = -1;
        String selection = TaskTable.KEY + " = ?";
        String[] selectionArgs = {String.valueOf(key)};
        try {
            res = sdb.delete(TaskTable.TABLE_NAME, selection, selectionArgs);
        } catch (Exception e) {
            throw e;
        }
        if (res != -1) return true;
        else return false;

    }


    private class TMDbHelper extends SQLiteOpenHelper {
        public static final String TAG = "TMDbHelper";
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "TimeManagement.db";
        //sql statement for creating task table
        private static final String SQL_CREATE_TASKENTRIES = "CREATE TABLE "
                + TaskTable.TABLE_NAME
                + " (" + TaskTable.KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TaskTable.COLUMN_NAME_TITLE + " STRING, "
                + TaskTable.COLUMN_NAME_DUEDATE + " STRING, "
                + TaskTable.COLUMN_NAME_UNITCODE + " STRING, "
                + TaskTable.COLUMN_NAME_TIME + " STRING, "
                + TaskTable.COLUMN_NAME_URGENCY + " STRING, "
                + TaskTable.COLUMN_NAME_IMPORTANT + " STRING, "
                + TaskTable.COLUMN_NAME_WEIGHT + " STRING, "
                + TaskTable.COLUMN_NAME_NOTIFY + " STRING, "
                + TaskTable.COLUMN_NAME_COMPLETE + " STRING, "
                + TaskTable.COLUMN_NAME_DETAIL + " STRING);";
        //sql statement for creating unitcode table
        private static final String SQL_CREATE_UNITCODEENTRIES = "CREATE TABLE "
                + UnitTable.TABLE_NAME
                + " (" + UnitTable.KEY + " INTEGER PRIMARY KEY AUTOINCREMENT,"
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
