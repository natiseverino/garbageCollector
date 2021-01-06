package carpinteroseverino.com.garbagecollector.DB.Task;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import carpinteroseverino.com.garbagecollector.DB.AppDatabase;
import carpinteroseverino.com.garbagecollector.DB.AppDatabaseListener;
import carpinteroseverino.com.garbagecollector.RecycledSubmission;

public class TaskGetAllRecycledSubmission extends AsyncTask<Void, Void, List<RecycledSubmission>> {

    String username;
    Context context;
    AppDatabaseListener listener;

    public TaskGetAllRecycledSubmission(String username, Context context, AppDatabaseListener listener) {
        this.username = username;
        this.context = context;
        this.listener = listener;
    }


    @Override
    protected List<RecycledSubmission> doInBackground(Void... voids) {
        List<RecycledSubmission> recycledSubmissions = AppDatabase.getAppDatabase(context).recycledSubmissionDao().getByUsername(username);
        return recycledSubmissions;
    }

    @Override
    protected void onPostExecute(List<RecycledSubmission> recycledSubmissions) {
        super.onPostExecute(recycledSubmissions);
        listener.setRecycledSubmissionFromDB(recycledSubmissions);
    }
}
