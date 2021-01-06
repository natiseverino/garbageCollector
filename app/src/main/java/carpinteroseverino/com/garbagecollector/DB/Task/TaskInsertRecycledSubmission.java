package carpinteroseverino.com.garbagecollector.DB.Task;

import android.content.Context;
import android.os.AsyncTask;

import carpinteroseverino.com.garbagecollector.DB.AppDatabase;
import carpinteroseverino.com.garbagecollector.RecycledSubmission;

public class TaskInsertRecycledSubmission extends AsyncTask<Void, Void, Void> {

    private Context context;
    private RecycledSubmission recycled;

    public TaskInsertRecycledSubmission(Context context, RecycledSubmission recycled) {
        this.context = context;
        this.recycled = recycled;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        AppDatabase.getAppDatabase(context).recycledSubmissionDao().insert(recycled);
        return null;
    }
}
