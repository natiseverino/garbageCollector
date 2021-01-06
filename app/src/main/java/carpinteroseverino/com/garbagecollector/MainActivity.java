package carpinteroseverino.com.garbagecollector;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import carpinteroseverino.com.garbagecollector.API.ApiHandler;
import carpinteroseverino.com.garbagecollector.API.ApiListener;
import carpinteroseverino.com.garbagecollector.API.RecyclingResponse.RecyclingResponseObj;
import carpinteroseverino.com.garbagecollector.API.TotalResponseObj;
import carpinteroseverino.com.garbagecollector.DB.AppDatabase;
import carpinteroseverino.com.garbagecollector.DB.AppDatabaseListener;
import carpinteroseverino.com.garbagecollector.DB.Task.TaskGetAllRecycledSubmission;
import carpinteroseverino.com.garbagecollector.DB.Task.TaskGetRecycledByUserName;
import carpinteroseverino.com.garbagecollector.DB.Task.TaskGetUserByUserName;
import carpinteroseverino.com.garbagecollector.DB.Task.TaskInsertRecycledSubmission;
import carpinteroseverino.com.garbagecollector.DB.Task.TaskUpdateRecycled;
import carpinteroseverino.com.garbagecollector.Fragments.FragmentActual;
import carpinteroseverino.com.garbagecollector.Fragments.FragmentHistory;
import carpinteroseverino.com.garbagecollector.Fragments.FragmentsListener;
import carpinteroseverino.com.garbagecollector.Fragments.PagerAdapter;

public class MainActivity extends AppCompatActivity implements AppDatabaseListener, NavigationView.OnNavigationItemSelectedListener, ApiListener, FragmentsListener {

    private static final String TAG = "MainActivity";
    public static final String SHARED_PREFERENCES_NAME = "carpinteroseverino.com.garbagecollector.SharedPreferences";

    private Toolbar toolbar;
    private FragmentActual fragmentActual;
    private FragmentHistory fragmentHistory;
    private static final int LOGIN_REQ_CODE = 485;

    private String actualUsername;
    private User actualUser;
    private Recycled actualRecycled;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    private ApiHandler apiHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        actualUsername = prefs.getString("actualUsername", "");

        String baseURL = getString(R.string.base_url);
        Log.d(TAG, "onCreate: Base URL: " + baseURL);
        Log.d(TAG, "onCreate: Actual username: " + actualUsername);

        apiHandler = new ApiHandler(this, baseURL);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_viewer);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        viewPager = findViewById(R.id.pager);

        if (savedInstanceState == null) {
            fragmentActual = new FragmentActual();
            fragmentHistory = new FragmentHistory();
        }

        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(fragmentActual, "Actual");
        pagerAdapter.addFragment(fragmentHistory, "Historial");
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    case 0:
                        navigationView.setCheckedItem(R.id.nav_actual);
                        toolbar.setSubtitle("Reciclados Actuales");
                        break;

                    case 1:
                        navigationView.setCheckedItem(R.id.nav_history);
                        toolbar.setSubtitle("Historial");
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        if (savedInstanceState == null) {
            viewPager.setCurrentItem(0);
            navigationView.setCheckedItem(R.id.nav_actual);
            toolbar.setSubtitle("Reciclados Actuales");
        }


    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: " + actualUsername);

        if (actualUsername.equals("")) { //No esta logueado
            startActivity(new Intent(this, LogInActivity.class));
            finish();
        } else {

            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                actualUser = extras.getParcelable("user");
                actualRecycled = extras.getParcelable("recycled");
            }


            if (actualUser != null && actualRecycled != null) { //Se acaba de registrar y llegaron por el bundle
                fragmentActual.setActualRecycled(actualRecycled);
                apiHandler.getTotal(actualUser.getUsername());
                updateDrawerHeader();
            } else {
                new TaskGetUserByUserName(this, actualUsername, this).execute();
                new TaskGetRecycledByUserName(this, actualUsername, this).execute();
            }
            fragmentHistory.setUsername(actualUsername);
        }
    }


    private void updateDrawerHeader() {
        View headerView = navigationView.getHeaderView(0);
        TextView name = headerView.findViewById(R.id.nav_header_name_lastname);
        TextView username = headerView.findViewById(R.id.nav_header_username);
        name.setText(actualUser.getFirstName() + " " + actualUser.getLastName());
        username.setText(actualUser.getUsername());
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();

    }

    private void savePreferences() {
        SharedPreferences.Editor editor = getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE).edit();
        editor.putString("actualUsername", actualUsername);
        editor.apply();
    }

    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: guardó");
        savePreferences();
        if (actualRecycled != null) //No user login on start case
            new TaskUpdateRecycled(this, actualRecycled).execute();
        super.onPause();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.nav_actual:
                viewPager.setCurrentItem(0);
                navigationView.setCheckedItem(R.id.nav_actual);
                toolbar.setSubtitle("Reciclados Actuales");
                break;

            case R.id.nav_history:
                viewPager.setCurrentItem(1);
                navigationView.setCheckedItem(R.id.nav_history);
                toolbar.setSubtitle("Historial");
                break;

            case R.id.nav_about_us:
                startActivity(new Intent(this, AboutUsActivity.class));
                break;

            case R.id.nav_logout:
                actualUsername = "";
                savePreferences();
                viewPager.setCurrentItem(0);
                navigationView.setCheckedItem(R.id.nav_actual);

                startActivity(new Intent(this, LogInActivity.class));
                finish();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void notifyUserNotFound() {
        Log.d(TAG, "notifyUserNotFound: ");
        apiHandler.postUser(actualUser);
    }

    @Override
    public void notifyUserUploaded(boolean isSuccessful) {
        Log.d(TAG, "notifyUserUploaded: isSuccessful: " + isSuccessful);
        if (isSuccessful) {
            new TaskGetAllRecycledSubmission(actualUser.getUsername(), this, this).execute();
        }
    }

    @Override
    public void setRecycledList(ArrayList<RecyclingResponseObj> list) {
    }

    @Override
    public void setTotalRecycled(TotalResponseObj totalRecycled) {
        if (fragmentHistory != null) {
            fragmentHistory.setTotalRecycled(totalRecycled);
        }
    }

    @Override
    public void setFragmentActual(FragmentActual fragmentActual) {
        this.fragmentActual = fragmentActual;
    }

    @Override
    public void onClickSubmissionButton() {
        int total = 0;
        total += actualRecycled.getBottles()
                + actualRecycled.getPaperboard()
                + actualRecycled.getGlass()
                + actualRecycled.getCans()
                + actualRecycled.getTetrabriks();
        if (total != 0) {
            RecycledSubmission recycledSubmission = new RecycledSubmission(actualRecycled);
            new TaskInsertRecycledSubmission(this, recycledSubmission).execute();
            apiHandler.postRecycledSubmission(actualRecycled.getUsername(), recycledSubmission);
        }
    }

    @Override
    public void setFragmentHistory(FragmentHistory fragmentHistory) {
        this.fragmentHistory = fragmentHistory;
    }

    @Override
    public void onClickHistoryListButton() {
        startActivity(new Intent(this, SubmissionListActivity.class));
    }



    @Override
    public void setAllUsersFromDB(List<User> users) {
    }

    @Override
    public void setUserFromDB(User user) {
        Log.d(TAG, "setUserFromDB: " + user);
        actualUser = user;
        apiHandler.getTotal(actualUser.getUsername());
        updateDrawerHeader();
    }

    @Override
    public void setRecycledFromDB(Recycled recycled) {
        Log.d(TAG, "setRecycledFromDB: " + recycled);
        actualRecycled = recycled;
        fragmentActual.setActualRecycled(actualRecycled);

    }

    @Override
    public void setRecycledSubmissionFromDB(List<RecycledSubmission> recycledSubmissions) {
        Log.d(TAG, "setRecycledSubmissionFromDB: ");
        for (RecycledSubmission submission : recycledSubmissions)
            apiHandler.postRecycledSubmission(actualUser.getUsername(), submission);
    }
}
