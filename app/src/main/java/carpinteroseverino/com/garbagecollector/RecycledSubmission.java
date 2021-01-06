package carpinteroseverino.com.garbagecollector;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity(tableName = "recycled-submission")
public class RecycledSubmission implements Parcelable{

    private static final String TAG = "RecycledSubmission";


    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "bottles")
    private int bottles;

    @ColumnInfo(name = "tetrabriks")
    private int tetrabriks;

    @ColumnInfo(name = "glass")
    private int glass;

    @ColumnInfo(name = "paperboard")
    private int paperboard;

    @ColumnInfo(name = "cans")
    private int cans;

    @ColumnInfo(name = "date")
    private String date;


    public RecycledSubmission(String username) {
        this.username = username;
        bottles = 0;
        tetrabriks = 0;
        glass = 0;
        paperboard = 0;
        cans = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = format.format(new Date());
    }

    @Ignore
    public RecycledSubmission(Recycled recycled){
        username = recycled.getUsername();
        bottles = recycled.getBottles();
        tetrabriks = recycled.getTetrabriks();
        glass = recycled.getGlass();
        paperboard = recycled.getPaperboard();
        cans = recycled.getCans();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = format.format(new Date());
    }

    protected RecycledSubmission(Parcel in) {
        id = in.readInt();
        username = in.readString();
        bottles = in.readInt();
        tetrabriks = in.readInt();
        glass = in.readInt();
        paperboard = in.readInt();
        cans = in.readInt();
        date = in.readString();
    }

    public static final Creator<RecycledSubmission> CREATOR = new Creator<RecycledSubmission>() {
        @Override
        public RecycledSubmission createFromParcel(Parcel in) {
            return new RecycledSubmission(in);
        }

        @Override
        public RecycledSubmission[] newArray(int size) {
            return new RecycledSubmission[size];
        }
    };

    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public int getBottles() {
        return bottles;
    }

    public void setBottles(int bottles) {
        this.bottles = bottles;
    }

    public int getTetrabriks() {
        return tetrabriks;
    }

    public void setTetrabriks(int tetrabriks) {
        this.tetrabriks = tetrabriks;
    }

    public int getGlass() {
        return glass;
    }

    public void setGlass(int glass) {
        this.glass = glass;
    }

    public int getPaperboard() {
        return paperboard;
    }

    public void setPaperboard(int paperboard) {
        this.paperboard = paperboard;
    }

    public int getCans() {
        return cans;
    }

    public void setCans(int cans) {
        this.cans = cans;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RecycledSubmission{" +
                "username='" + username + '\'' +
                ", bottles=" + bottles +
                ", tetrabriks=" + tetrabriks +
                ", glass=" + glass +
                ", paperboard=" + paperboard +
                ", cans=" + cans +
                ", date='" + date + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(username);
        dest.writeInt(bottles);
        dest.writeInt(tetrabriks);
        dest.writeInt(glass);
        dest.writeInt(paperboard);
        dest.writeInt(cans);
        dest.writeString(date);
    }
}
