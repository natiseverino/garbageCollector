package carpinteroseverino.com.garbagecollector.DB.Task;

import android.content.Context;
import android.os.AsyncTask;

import carpinteroseverino.com.garbagecollector.DB.AppDatabase;
import carpinteroseverino.com.garbagecollector.DB.AppDatabaseListener;
import carpinteroseverino.com.garbagecollector.User;

public class TaskGetUserByUserName extends AsyncTask<Void, Void, User> {

    Context context;
    String userName;
    AppDatabaseListener listener;

    public TaskGetUserByUserName(Context context, String userName, AppDatabaseListener listener) {
        this.context = context;
        this.userName = userName;
        this.listener = listener;
    }

    @Override
    protected User doInBackground(Void... voids) {
        User user = AppDatabase.getAppDatabase(context).userDao().getByUserName(userName);
        return user;
    }


    @Override
    protected void onPostExecute(User user) {
        super.onPostExecute(user);
        listener.setUserFromDB(user);
    }
}
