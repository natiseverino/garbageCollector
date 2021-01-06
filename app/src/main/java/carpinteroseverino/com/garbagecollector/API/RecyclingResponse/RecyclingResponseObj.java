
package carpinteroseverino.com.garbagecollector.API.RecyclingResponse;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import carpinteroseverino.com.garbagecollector.Recycled;

public class RecyclingResponseObj implements Parcelable, Comparable<RecyclingResponseObj>
{

    private int id;
    @SerializedName("user")
    private UserApi user;
    private int bottles;
    private int tetrabriks;
    private int glass;
    private int paperboard;
    private int cans;
    private String date;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Creator<RecyclingResponseObj> CREATOR = new Creator<RecyclingResponseObj>() {


        @SuppressWarnings({
            "unchecked"
        })
        public RecyclingResponseObj createFromParcel(Parcel in) {
            return new RecyclingResponseObj(in);
        }

        public RecyclingResponseObj[] newArray(int size) {
            return (new RecyclingResponseObj[size]);
        }

    }
    ;

    protected RecyclingResponseObj(Parcel in) {
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.user = ((UserApi) in.readValue((UserApi.class.getClassLoader())));
        this.bottles = ((int) in.readValue((int.class.getClassLoader())));
        this.tetrabriks = ((int) in.readValue((int.class.getClassLoader())));
        this.glass = ((int) in.readValue((int.class.getClassLoader())));
        this.paperboard = ((int) in.readValue((int.class.getClassLoader())));
        this.cans = ((int) in.readValue((int.class.getClassLoader())));
        this.date = ((String) in.readValue((String.class.getClassLoader())));
        this.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
    }

    public RecyclingResponseObj() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserApi getUser() {
        return user;
    }

    public void setUser(UserApi user) {
        this.user = user;
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

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(user);
        dest.writeValue(bottles);
        dest.writeValue(tetrabriks);
        dest.writeValue(glass);
        dest.writeValue(paperboard);
        dest.writeValue(cans);
        dest.writeValue(date);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }


    @Override
    public String toString() {
        return "RecyclingResponseObj{" +
                "id=" + id +
                ", user=" + user.getUsername() +
                ", bottles=" + bottles +
                ", tetrabriks=" + tetrabriks +
                ", glass=" + glass +
                ", paperboard=" + paperboard +
                ", cans=" + cans +
                ", date='" + date + '\'' +
                '}';
    }


    @Override
    public int compareTo(@NonNull RecyclingResponseObj o) {
        if (this.id > o.getId())
            return 1;
        else if (this.id < o.getId())
            return -1;
            else
                return 0;
    }
}
