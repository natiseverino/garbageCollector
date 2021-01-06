package carpinteroseverino.com.garbagecollector;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import carpinteroseverino.com.garbagecollector.DB.AppDatabaseListener;
import carpinteroseverino.com.garbagecollector.DB.Task.TaskGetAllUsers;
import carpinteroseverino.com.garbagecollector.DB.Task.TaskInsertRecycled;
import carpinteroseverino.com.garbagecollector.DB.Task.TaskInsertUser;

import static carpinteroseverino.com.garbagecollector.MainActivity.SHARED_PREFERENCES_NAME;

public class LogInActivity extends AppCompatActivity implements AppDatabaseListener {

    private static final String TAG = "LogInActivity";

    private Button loginButton;
    private Button signinButton;
    private Spinner spinner;
    private ArrayAdapter<String> spinnerAdapter;
    private ArrayList<String> userNames;
    private ArrayList<User> users;
    private Intent resultData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        new TaskGetAllUsers(this, this).execute();

        loginButton = findViewById(R.id.login_button);
        signinButton = findViewById(R.id.signin_button);
        spinner = findViewById(R.id.spinner);
        spinnerAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item_user);
        spinner.setAdapter(spinnerAdapter);
        loginButton.setEnabled(false);

        userNames = new ArrayList<>();
        users = new ArrayList<>();


        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final User user = new User();

                AlertDialog.Builder builder = new AlertDialog.Builder(LogInActivity.this);
                LayoutInflater inflater = LogInActivity.this.getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.dialog_new_user, null);
                final EditText editTextFirstName = dialogView.findViewById(R.id.firstname);
                final EditText editTextLastName = dialogView.findViewById(R.id.lastname);
                final EditText editTextUserName = dialogView.findViewById(R.id.username);
                final EditText editTextMail = dialogView.findViewById(R.id.mail);
                final EditText editTextDepartment = dialogView.findViewById(R.id.department);
                final EditText editTextStreetName = dialogView.findViewById(R.id.streetname);
                final EditText editTextNumber = dialogView.findViewById(R.id.number);
                final EditText editTextCity = dialogView.findViewById(R.id.city);
                final EditText editTextState = dialogView.findViewById(R.id.state);
                final EditText editTextZipCode = dialogView.findViewById(R.id.zipcode);
                builder.setView(dialogView)
                        .setTitle("Nuevo Usuario")
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("Ok", null);

                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        if (!userNames.contains(user.getUsername())) {
                            dialog.dismiss();
                        }
                    }
                });

                final AlertDialog alertDialog = builder.create();
                alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                        positiveButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String firstName = editTextFirstName.getText().toString();
                                String lastName = editTextLastName.getText().toString();
                                String userName = editTextUserName.getText().toString();
                                String mail = editTextMail.getText().toString();
                                String department = editTextDepartment.getText().toString();
                                String streetName = editTextStreetName.getText().toString();
                                String number = editTextNumber.getText().toString();
                                String city = editTextCity.getText().toString();
                                String state = editTextState.getText().toString();
                                String zipCode = editTextZipCode.getText().toString();

                                if (firstName.equals("") ||
                                        lastName.equals("") ||
                                        userName.equals("") ||
                                        mail.equals("") ||
                                        department.equals("") ||
                                        streetName.equals("") ||
                                        number.equals("") ||
                                        city.equals("") ||
                                        state.equals("") ||
                                        zipCode.equals("")){
                                    Toast.makeText(LogInActivity.this, "Ninguno de los campos puede estar vacío", Toast.LENGTH_LONG).show();
                                    return;
                                }
                                    if (userNames.contains(userName) || userName.equals("")) {
                                        Toast.makeText(LogInActivity.this, "El nombre de usuario es invalido.\n Ingrese uno nuevo.", Toast.LENGTH_LONG).show();
                                        return;
                                    }

                                if (number.equals(""))
                                    number = "0";

                                user.setFirstName(firstName);
                                user.setLastName(lastName);
                                user.setUsername(userName);
                                user.setMail(mail);
                                user.setDepartment(department);
                                user.setStreetAddress(streetName);
                                user.setNumber(number);
                                user.setCity(city);
                                user.setState(state);
                                user.setZipCode(zipCode);

                                Recycled recycled = new Recycled(user.getUsername());
                                new TaskInsertUser(getBaseContext(), user).execute();
                                new TaskInsertRecycled(getBaseContext(), recycled).execute();

                                savePreferences(userName);
                                Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putParcelable("user", user);
                                bundle.putParcelable("recycled", recycled);
                                intent.putExtras(bundle);

                                alertDialog.dismiss();
                                startActivity(intent);
                                finish();
                            }
                        });
                    }
                });
                alertDialog.show();
            }

        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = spinner.getSelectedItem().toString();
                savePreferences(userName);

                startActivity(new Intent(LogInActivity.this, MainActivity.class));
                finish();
            }
        });

        initSignInUs();

    }


    @Override
    public void setAllUsersFromDB(List<User> users) {
        userNames = new ArrayList<>();
        this.users = new ArrayList<>(users);
        for (User user : users)
            userNames.add(user.getUsername());

        spinnerAdapter.addAll(userNames);
        spinnerAdapter.notifyDataSetChanged();
        if (!userNames.isEmpty())
            loginButton.setEnabled(true);
    }

    @Override
    public void setUserFromDB(User user) {
    }

    @Override
    public void setRecycledFromDB(Recycled recycled) {
        resultData.putExtra("recycled", recycled);
        setResult(RESULT_OK, resultData);
        finish();
    }

    @Override
    public void setRecycledSubmissionFromDB(List<RecycledSubmission> recycledSubmissions) {
    }

    private void savePreferences(String username) {
        SharedPreferences.Editor editor = getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE).edit();
        editor.putString("actualUsername", username);
        editor.apply();
    }

    private void initSignInUs() {
        final LinearLayout signInUs = findViewById(R.id.signin_us_layout);
        Button bautista = findViewById(R.id.signin_bautista_button);
        Button natalia = findViewById(R.id.signin_natalia_button);

        signinButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (signInUs.getVisibility() == View.GONE)
                    signInUs.setVisibility(View.VISIBLE);
                else
                    signInUs.setVisibility(View.GONE);
                return true;
            }
        });

        bautista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = "BautistaCRP";
                if (!userNames.contains(username)) {

                    User user = new User();
                    user.setUsername(username);
                    user.setFirstName("Bautista");
                    user.setLastName("Carpintero");
                    user.setMail("bj.carpintero@gmail.com");
                    user.setDepartment("Tandil");
                    user.setStreetAddress("11 de Septiembre");
                    user.setNumber("706");
                    user.setCity("Tandil");
                    user.setState("Buenos Aires");
                    user.setZipCode("7000");

                    Recycled recycled = new Recycled(user.getUsername());
                    new TaskInsertUser(getBaseContext(), user).execute();
                    new TaskInsertRecycled(getBaseContext(), recycled).execute();

                    savePreferences(username);
                    Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("user", user);
                    bundle.putParcelable("recycled", recycled);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }

            }
        });

        natalia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = "NatiSV";
                if (!userNames.contains(username)) {
                    User user = new User();
                    user.setUsername(username);
                    user.setFirstName("Natalia");
                    user.setLastName("Severino");
                    user.setMail("natiseverino96@gmail.com");
                    user.setDepartment("Tandil");
                    user.setStreetAddress("Moreno");
                    user.setNumber("60");
                    user.setCity("Tandil");
                    user.setState("Buenos Aires");
                    user.setZipCode("7000");

                    Recycled recycled = new Recycled(user.getUsername());
                    new TaskInsertUser(getBaseContext(), user).execute();
                    new TaskInsertRecycled(getBaseContext(), recycled).execute();

                    savePreferences(username);
                    Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("user", user);
                    bundle.putParcelable("recycled", recycled);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}