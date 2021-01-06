package carpinteroseverino.com.garbagecollector.API;
import java.util.HashMap;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class TotalResponseObj implements Parcelable
{

    private int tons;
    private int bottles;
    private int tetrabriks;
    private int glass;
    private int paperboard;
    private int cans;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @Override
    public String toString() {
        return "TotalResponseObj{" +
                "tons=" + tons +
                ", bottles=" + bottles +
                ", tetrabriks=" + tetrabriks +
                ", glass=" + glass +
                ", paperboard=" + paperboard +
                ", cans=" + cans +
                '}';
    }

    public final static Parcelable.Creator<TotalResponseObj> CREATOR = new Creator<TotalResponseObj>() {


        @SuppressWarnings({
                "unchecked"
        })
        public TotalResponseObj createFromParcel(Parcel in) {
            return new TotalResponseObj(in);
        }

        public TotalResponseObj[] newArray(int size) {
            return (new TotalResponseObj[size]);
        }

    }
            ;

    protected TotalResponseObj(Parcel in) {
        this.tons = ((int) in.readValue((int.class.getClassLoader())));
        this.bottles = ((int) in.readValue((int.class.getClassLoader())));
        this.tetrabriks = ((int) in.readValue((int.class.getClassLoader())));
        this.glass = ((int) in.readValue((int.class.getClassLoader())));
        this.paperboard = ((int) in.readValue((int.class.getClassLoader())));
        this.cans = ((int) in.readValue((int.class.getClassLoader())));
        this.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
    }

    public TotalResponseObj() {
    }

    public int getTons() {
        return tons;
    }

    public void setTons(int tons) {
        this.tons = tons;
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

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(tons);
        dest.writeValue(bottles);
        dest.writeValue(tetrabriks);
        dest.writeValue(glass);
        dest.writeValue(paperboard);
        dest.writeValue(cans);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return 0;
    }

}