package carpinteroseverino.com.garbagecollector.API;

import java.util.ArrayList;

import carpinteroseverino.com.garbagecollector.API.RecyclingResponse.RecyclingResponseObj;
import carpinteroseverino.com.garbagecollector.API.RecyclingResponse.UserApi;
import carpinteroseverino.com.garbagecollector.RecycledSubmission;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @GET("api/users/{username}/recycling/")
    Call<ArrayList<RecyclingResponseObj>> getRecyclingList(@Path("username") String username);


    @GET("api/users/{username}/total/")
    Call<TotalResponseObj> getTotal(@Path("username") String username);

    @Headers({"Content-Type: application/json"})
    @POST("api/users")
    Call<ResponseBody> postUser(@Body UserApi userApi);

    @Headers({"Content-Type: application/json"})
    @POST("api/users/{username}/recycling/")
    Call<ResponseBody> postRecycledSubmission(@Path("username") String username, @Body RecycledSubmission recycledSubmission);


}
