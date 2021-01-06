package carpinteroseverino.com.garbagecollector.DB.Task;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import carpinteroseverino.com.garbagecollector.DB.AppDatabase;
import carpinteroseverino.com.garbagecollector.User;

public class TaskInsertAllUsers extends AsyncTask<Void, Void, Void> {

    Context context;
    List<User> users;

    public TaskInsertAllUsers(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        AppDatabase.getAppDatabase(context).userDao().insertAll(users);
        return null;
    }
}
