package carpinteroseverino.com.garbagecollector.DB;

import java.util.List;

import carpinteroseverino.com.garbagecollector.Recycled;
import carpinteroseverino.com.garbagecollector.RecycledSubmission;
import carpinteroseverino.com.garbagecollector.User;

public interface AppDatabaseListener {
    void setAllUsersFromDB(List<User> users);
    void setUserFromDB(User user);

    void setRecycledFromDB(Recycled recycled);
    void setRecycledSubmissionFromDB(List<RecycledSubmission> recycledSubmissions);

}
