package assignment2.kit607.timemanagement;

import android.provider.BaseColumns;

/**
 * Created by Allen on 5/6/2017.
 */

class TaskTable {
    public static final String TABLE_NAME = "Task";
    public static final String Key = "_id";
    public static final String COLUMN_NAME_TITLE = "Title";
    public static final String COLUMN_NAME_DUEDATE = "Duedate";
    public static final String COLUMN_NAME_UNITCODE = "UnitCode";
    public static final String COLUMN_NAME_URGENCY = "Urgency";
    public static final String COLUMN_NAME_IMPORTANT = "Important";
    public static final String COLUMN_NAME_WEIGHT = "Weight";
    public static final String COLUMN_NAME_NOTIFY = "Notify";
    public static final String COLUMN_NAME_DETAIL = "Detail";
    public static final String COLUMN_NAME_TMQSCORE = "TMQ_Score";

}

class UnitTable {
    public static final String TABLE_NAME = "UnitCode";
    public static final String Key = "_id";
    public static final String COLUMN_NAME_UNITID = "UnitId";
    public static final String COLUMN_NAME_UNITNAME = "Unitname";
}

class TMQTable{
    public static final String TABLE_NAME = "TMQ";
    public static final String Key = "_id";
    public static final String COLUMN_NAME_CATEGORY = "Category";
    public static final String COLUMN_NAME_QUESTION = "Question";
    public static final String COLUMN_NAME_SCORE = "Score";

}