package assignment2.kit607.timemanagement;

/**
 * Created by Allen on 5/6/2017.
 */

class Task {
    private int TaskId;
    private String Title;
    private String Duedate;
    private String Time;
    private Unit Unit;
    private String Urgency;
    private String Important;
    private String Weight;
    private String Notify;
    private String Detail;
    private DBAdapter db;
    public Task(){}

    public int getTaskId() {
        return TaskId;
    }

    public void setTaskId(int taskId) {
        this.TaskId = taskId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getDuedate() { return Duedate; }

    public void setDuedate(String duedate) {
        this.Duedate = duedate;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        this.Time = time;
    }

    public Unit get_unitCode() {
        return Unit;
    }

    public void set_unitCode(Unit unit) {
        this.Unit = unit;
    }

    public String getImportant() {
        return Important;
    }

    public void setImportant(String important) {
        this.Important = important;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        this.Weight = weight;
    }

    public String is_notify() {
        return Notify;
    }

    public void setNotify(String notify) {
        this.Notify = notify;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        this.Detail = detail;
    }

    public String getUrgency() {
        return Urgency;
    }

    public void setUrgency(String urgency) {
        this.Urgency = urgency;
    }
}

class Unit{
    private String UnitId;
    private String UnitName;
    public Unit(){};

    public String getUnitId() {
        return UnitId;
    }

    public void setUnitId(String unitId) {
        this.UnitId = unitId;
    }

    public String getUnitName() {
        return UnitName;
    }

    public void setUnitName(String unitName) {
        this.UnitName = unitName;
    }
}