
package carpinteroseverino.com.garbagecollector.API.RecyclingResponse;

import java.util.HashMap;
import java.util.Map;

import android.os.Parcel;
import android.os.Parcelable;

import carpinteroseverino.com.garbagecollector.User;


public class UserApi implements Parcelable {

    private Address address;
    private String firstName;
    private int id;
    private String lastName;
    private String mail;
    private String username;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Creator<UserApi> CREATOR = new Creator<UserApi>() {


        @SuppressWarnings({
                "unchecked"
        })
        public UserApi createFromParcel(Parcel in) {
            return new UserApi(in);
        }

        public UserApi[] newArray(int size) {
            return (new UserApi[size]);
        }

    };

    protected UserApi(Parcel in) {
        this.address = ((Address) in.readValue((Address.class.getClassLoader())));
        this.firstName = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.lastName = ((String) in.readValue((String.class.getClassLoader())));
        this.mail = ((String) in.readValue((String.class.getClassLoader())));
        this.username = ((String) in.readValue((String.class.getClassLoader())));
        this.additionalProperties = ((Map<String, Object>) in.readValue((Map.class.getClassLoader())));
    }

    public UserApi() {
    }

    public UserApi(User user) {
        address = new Address();
        address.setCity(user.getCity());
        address.setDepartment(user.getDepartment());
        address.setNumber(Integer.parseInt(user.getNumber()));
        address.setState(user.getState());
        address.setStreetAddress(user.getStreetAddress());
        address.setZipCode(user.getZipCode());
        username = user.getUsername();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        mail = user.getMail();

    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(address);
        dest.writeValue(firstName);
        dest.writeValue(id);
        dest.writeValue(lastName);
        dest.writeValue(mail);
        dest.writeValue(username);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return 0;
    }

}
