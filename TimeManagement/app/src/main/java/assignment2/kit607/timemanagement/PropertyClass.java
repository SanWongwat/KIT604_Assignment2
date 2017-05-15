package assignment2.kit607.timemanagement;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Allen on 5/6/2017.
 */

class Task implements Parcelable {
    private int Key;
    private String Title;
    private String Duedate;
    private String Time;
    private Unit Unit;
    private String Urgency;
    private String Important;
    private String Weight;
    private String Notify;
    private String Detail;
    private String Completion;

    public Task() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeParcelable(Unit, flags);
        out.writeInt(Key);
        out.writeString(Title);
        out.writeString(Duedate);
        out.writeString(Time);
        out.writeString(Urgency);
        out.writeString(Important);
        out.writeString(Weight);
        out.writeString(Notify);
        out.writeString(Detail);
        out.writeString(Completion);
    }
    private Task(Parcel in) {
        Unit = in.readParcelable(Unit.class.getClassLoader());
        Key = in.readInt();
        Title = in.readString();
        Duedate = in.readString();
        Time = in.readString();
        Urgency = in.readString();
        Important = in.readString();
        Weight = in.readString();
        Notify = in.readString();
        Detail = in.readString();
        Completion = in.readString();
    }
    public static final Parcelable.Creator<Task> CREATOR = new Parcelable.Creator<Task>() {
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        public Task[] newArray(int size) {
            return new Task[size];
        }
    };



    public int getKey() {
        return Key;
    }

    public void setKey(int key) {
        this.Key = key;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getDuedate() {
        return Duedate;
    }

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

    public String isNotify() {
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

    public String getCompletion() {
        return Completion;
    }

    public void setCompletion(String completion) {
        this.Completion = completion;
    }
}

class Unit implements Parcelable {
    private int Key;
    private String UnitId;
    private String UnitName;

    public Unit() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(Key);
        out.writeString(UnitId);
        out.writeString(UnitName);
    }
    private Unit(Parcel in) {
        Key = in.readInt();
        UnitId = in.readString();
        UnitName = in.readString();
    }
    public static final Parcelable.Creator<Unit> CREATOR = new Parcelable.Creator<Unit>() {
        public Unit createFromParcel(Parcel in) {
            return new Unit(in);
        }

        public Unit[] newArray(int size) {
            return new Unit[size];
        }
    };



    public int getKey() {
        return Key;
    }

    public void setKey(int key) {
        Key = key;
    }

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