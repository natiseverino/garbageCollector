package carpinteroseverino.com.garbagecollector.DB.Task;

import android.content.Context;
import android.os.AsyncTask;

import carpinteroseverino.com.garbagecollector.DB.AppDatabase;
import carpinteroseverino.com.garbagecollector.Recycled;

public class TaskInsertRecycled  extends AsyncTask<Void, Void, Void> {

    private Context context;
    private Recycled recycled;

    public TaskInsertRecycled(Context context, Recycled recycled) {
        this.context = context;
        this.recycled = recycled;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        AppDatabase.getAppDatabase(context).recycledDao().insert(recycled);
        return null;
    }
}
