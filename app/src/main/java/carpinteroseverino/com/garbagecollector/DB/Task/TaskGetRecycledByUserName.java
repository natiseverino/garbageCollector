package carpinteroseverino.com.garbagecollector.DB.Task;

import android.content.Context;
import android.os.AsyncTask;

import carpinteroseverino.com.garbagecollector.DB.AppDatabase;
import carpinteroseverino.com.garbagecollector.DB.AppDatabaseListener;
import carpinteroseverino.com.garbagecollector.Recycled;

public class TaskGetRecycledByUserName extends AsyncTask<Void, Void, Recycled> {

    private Context context;
    private String userName;
    private AppDatabaseListener listener;

    public TaskGetRecycledByUserName(Context context, String userName, AppDatabaseListener listener) {
        this.context = context;
        this.userName = userName;
        this.listener = listener;
    }

    @Override
    protected Recycled doInBackground(Void... voids) {
        Recycled recycled = AppDatabase.getAppDatabase(context).recycledDao().getByUsername(userName);
        return recycled;
    }

    @Override
    protected void onPostExecute(Recycled recycled) {
        super.onPostExecute(recycled);
        listener.setRecycledFromDB(recycled);
    }
}
