package carpinteroseverino.com.garbagecollector;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;

import static android.content.ContentValues.TAG;

@Entity(tableName = "recycled")
public class Recycled implements Parcelable{

    @PrimaryKey
    @NonNull
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

    public Recycled(String username) {
        this.username = username;
        bottles = 0;
        tetrabriks = 0;
        glass = 0;
        paperboard = 0;
        cans = 0;
    }

    @Ignore
    public Recycled(String username, int bottles, int tetrabriks, int glass, int paperboard, int cans) {
        this.username = username;
        this.bottles = bottles;
        this.tetrabriks = tetrabriks;
        this.glass = glass;
        this.paperboard = paperboard;
        this.cans = cans;
    }


    protected Recycled(Parcel in) {
        username = in.readString();
        bottles = in.readInt();
        tetrabriks = in.readInt();
        glass = in.readInt();
        paperboard = in.readInt();
        cans = in.readInt();
    }

    public static final Creator<Recycled> CREATOR = new Creator<Recycled>() {
        @Override
        public Recycled createFromParcel(Parcel in) {
            return new Recycled(in);
        }

        @Override
        public Recycled[] newArray(int size) {
            return new Recycled[size];
        }
    };

    @Override
    public String toString() {
        return "Recycled{" +
                "username = " + username +
                ", bottles = " + bottles +
                ", paperboard = " + paperboard +
                ", glass = " + glass +
                ", cans = " + cans +
                ", tetrabriks = " + tetrabriks +
                '}';
    }

    public void setBottlesPP() {
        bottles++;
    }

    public void setBottlesSS() {
        bottles--;
        if (bottles < 0) bottles = 0;
    }

    public int getBottles() {
        return bottles;
    }

    public void setBottles(int bottles) {
        this.bottles = bottles;
        if (bottles < 0) bottles = 0;
    }



    public void setTetrabriksPP() {
        tetrabriks++;
    }

    public void setTetrabriksSS() {
        tetrabriks--;
        if (tetrabriks < 0) tetrabriks = 0;
    }

    public int getTetrabriks() {
        return tetrabriks;
    }

    public void setTetrabriks(int tetrabriks) {
        this.tetrabriks = tetrabriks;
        if (tetrabriks < 0) tetrabriks = 0;
    }



    public void setGlassPP() {
        glass++;
    }

    public void setGlassSS() {
        glass--;
        if (glass < 0) glass = 0;
    }

    public int getGlass() {
        return glass;
    }

    public void setGlass(int glass) {
        this.glass = glass;
        if (glass < 0) glass = 0;
    }



    public void setPaperboardPP() {
        paperboard++;
    }

    public void setPaperboardSS() {
        paperboard--;
        if (paperboard < 0) paperboard = 0;
    }

    public int getPaperboard() {
        return paperboard;
    }

    public void setPaperboard(int paperboard) {
        this.paperboard = paperboard;
    }



    public void setCansPP() {
        cans++;
    }

    public void setCansSS() {
        cans--;
        if (cans < 0) cans = 0;
    }

    public int getCans() {
        return cans;
    }

    public void setCans(int cans) {
        this.cans = cans;
        if (cans < 0) cans = 0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeInt(bottles);
        dest.writeInt(tetrabriks);
        dest.writeInt(glass);
        dest.writeInt(paperboard);
        dest.writeInt(cans);
    }

    public void reset(){
        bottles = 0;
        tetrabriks = 0;
        glass = 0;
        paperboard = 0;
        cans = 0;
    }
}
