package carpinteroseverino.com.garbagecollector.DB.Task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import carpinteroseverino.com.garbagecollector.DB.AppDatabase;
import carpinteroseverino.com.garbagecollector.Recycled;

public class TaskUpdateRecycled  extends AsyncTask<Void,Void,Void> {

    private static final String TAG = "TaskUpdateRecycled";

    private Context context;
    private Recycled recycled;

    public TaskUpdateRecycled(Context context, Recycled recycled) {
        this.context = context;
        this.recycled = recycled;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        AppDatabase.getAppDatabase(context).recycledDao().updateRecycled(recycled);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Log.d(TAG, "onPostExecute: This recycled was updated: "+recycled );
        super.onPostExecute(aVoid);
    }
}
