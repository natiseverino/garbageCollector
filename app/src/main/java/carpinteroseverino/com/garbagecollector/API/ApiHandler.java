package carpinteroseverino.com.garbagecollector.API;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import carpinteroseverino.com.garbagecollector.API.RecyclingResponse.RecyclingResponseObj;
import carpinteroseverino.com.garbagecollector.API.RecyclingResponse.UserApi;
import carpinteroseverino.com.garbagecollector.RecycledSubmission;
import carpinteroseverino.com.garbagecollector.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class ApiHandler {

    private static final String TAG = "ApiHandler";

    private static final String USER_NOT_FOUND_CODE = "USER_NOT_FOUND";

    private Retrofit retrofit;
    private ApiListener listener;

    public ApiHandler(ApiListener listener, String baseUrl) {
        this.listener = listener;
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


    }

    public void getRecyclingList(String username){
        ApiService service = retrofit.create(ApiService.class);
        final Call<ArrayList<RecyclingResponseObj>> response = service.getRecyclingList(username);
        response.enqueue(new Callback<ArrayList<RecyclingResponseObj>>() {
            @Override
            public void onResponse(Call<ArrayList<RecyclingResponseObj>> call, Response<ArrayList<RecyclingResponseObj>> response) {

                if (response.isSuccessful()) {
                    ArrayList<RecyclingResponseObj> bodyArray = response.body();
                    Log.d(TAG, "onResponse: " + bodyArray);
                    listener.setRecycledList(bodyArray);

                } else {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        String code = jsonObject.get("code").toString();
                        Log.d(TAG, "onResponse: failCode: "+code);
                        if(code.equals(USER_NOT_FOUND_CODE))
                            listener.notifyUserNotFound();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<RecyclingResponseObj>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }

        });
    }



    public void getTotal(String username){
        ApiService service = retrofit.create(ApiService.class);
        final Call<TotalResponseObj> response = service.getTotal(username);
        response.enqueue(new Callback<TotalResponseObj>() {
            @Override
            public void onResponse(Call<TotalResponseObj> call, Response<TotalResponseObj> response) {
                Log.d(TAG, "onResponse: Code: "+response.code());
                if (response.isSuccessful()) {
                    TotalResponseObj totalResponseObj = response.body();
                    Log.d(TAG, "onResponse: " + totalResponseObj);
                    listener.setTotalRecycled(totalResponseObj);
                } else {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        String code = jsonObject.get("code").toString();
                        Log.d(TAG, "onResponse: failCode: "+code);
                        if(code.equals(USER_NOT_FOUND_CODE))
                            listener.notifyUserNotFound();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<TotalResponseObj> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    public void postUser(User user){
        UserApi userApi = new UserApi(user);
        ApiService service = retrofit.create(ApiService.class);
        Call<ResponseBody> call = service.postUser(userApi);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG, "onResponse: Successful: " + response.isSuccessful());
                listener.notifyUserUploaded(response.isSuccessful());
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }


    public void postRecycledSubmission(final String username, RecycledSubmission recycledSubmission){
        ApiService service = retrofit.create(ApiService.class);
        Log.d(TAG, "postRecycledSubmission: submission: "+recycledSubmission);
        Call<ResponseBody> call = service.postRecycledSubmission(username, recycledSubmission);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG, "onResponse: Successful: "+response.isSuccessful());

                if(response.isSuccessful()){
                    // Se acaba de agregar una submission al server
                    // Vuelvo a pedir el total, que va a llegar a la mainActivity y actuliza el grafico
                    ApiHandler.this.getTotal(username);
                } else {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        String code = jsonObject.get("code").toString();
                        Log.d(TAG, "onResponse: failCode: "+code);
                        if(code.equals(USER_NOT_FOUND_CODE))
                            listener.notifyUserNotFound();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }


    public ApiListener getListener() {
        return listener;
    }

    public void setListener(ApiListener listener) {
        this.listener = listener;
    }
}
