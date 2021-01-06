package carpinteroseverino.com.garbagecollector;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import carpinteroseverino.com.garbagecollector.API.ApiHandler;
import carpinteroseverino.com.garbagecollector.API.ApiListener;
import carpinteroseverino.com.garbagecollector.API.RecyclingResponse.RecyclingResponseObj;
import carpinteroseverino.com.garbagecollector.API.TotalResponseObj;

import static carpinteroseverino.com.garbagecollector.MainActivity.SHARED_PREFERENCES_NAME;

public class SubmissionListActivity  extends AppCompatActivity implements ApiListener {

    private static final String TAG = "SubmissionListActivity";

    private ApiHandler apiHandler;

    private String username;
    private String baseURL;
    private RecyclerView recyclerView;
    private SubmissionListAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission_list);

        recyclerView = findViewById(R.id.recycler_view);
        adapter = new SubmissionListAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        username = prefs.getString("actualUsername", "");

        baseURL =  getString(R.string.base_url);
        apiHandler = new ApiHandler(this, baseURL);
        apiHandler.getRecyclingList(username);

    }

    @Override
    public void notifyUserNotFound() {}

    @Override
    public void notifyUserUploaded(boolean isSuccessful) {}

    @Override
    public void setRecycledList(ArrayList<RecyclingResponseObj> list) {
        adapter.setDataset(list);
    }

    @Override
    public void setTotalRecycled(TotalResponseObj totalRecycled) {

    }
}
