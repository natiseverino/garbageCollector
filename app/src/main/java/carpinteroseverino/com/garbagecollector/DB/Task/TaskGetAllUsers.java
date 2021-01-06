package carpinteroseverino.com.garbagecollector.DB.Task;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import carpinteroseverino.com.garbagecollector.DB.AppDatabase;
import carpinteroseverino.com.garbagecollector.DB.AppDatabaseListener;
import carpinteroseverino.com.garbagecollector.User;

public class TaskGetAllUsers extends AsyncTask<Void, Void, List<User>> {

    Context context;
    AppDatabaseListener listener;

    public TaskGetAllUsers(Context context, AppDatabaseListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected List<User> doInBackground(Void... voids) {
        List<User> users = AppDatabase.getAppDatabase(context).userDao().getAll();
        return users;
    }

    @Override
    protected void onPostExecute(List<User> users) {
        super.onPostExecute(users);
        listener.setAllUsersFromDB(users);
    }
}
