package carpinteroseverino.com.garbagecollector.API;

import java.util.ArrayList;

import carpinteroseverino.com.garbagecollector.API.RecyclingResponse.RecyclingResponseObj;

public interface ApiListener {

    void notifyUserNotFound();
    void notifyUserUploaded(boolean isSuccessful);
    void setRecycledList(ArrayList<RecyclingResponseObj> list);
    void setTotalRecycled(TotalResponseObj totalRecycled);

}
