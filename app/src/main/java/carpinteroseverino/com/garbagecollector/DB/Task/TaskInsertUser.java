package carpinteroseverino.com.garbagecollector.DB.Task;

import android.content.Context;
import android.os.AsyncTask;

import carpinteroseverino.com.garbagecollector.DB.AppDatabase;
import carpinteroseverino.com.garbagecollector.User;

public class TaskInsertUser extends AsyncTask<Void, Void, Void> {

    Context context;
    User user;

    public TaskInsertUser(Context context, User user) {
        this.context = context;
        this.user = user;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        AppDatabase.getAppDatabase(context).userDao().insert(user);
        return null;
    }
}
