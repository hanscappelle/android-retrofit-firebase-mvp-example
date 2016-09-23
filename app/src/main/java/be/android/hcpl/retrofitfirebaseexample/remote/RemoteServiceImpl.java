package be.android.hcpl.retrofitfirebaseexample.remote;

import java.util.Map;

import be.android.hcpl.retrofitfirebaseexample.BuildConfig;
import be.android.hcpl.retrofitfirebaseexample.model.Task;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public class RemoteServiceImpl {

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BuildConfig.REMOTE_SERVICE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // use gson converter
            .build();

    private static RemoteService service = null;

    public static RemoteService getInstance() {
        if (service == null) {
            service = retrofit.create(RemoteService.class);
        }
        return service;
    }

    public interface RemoteService {

        @PUT("/task/{title}.json")
        Call<Task> createTask(
                // title configured as identifier for tasks
                @Path("title") String title,
                @Body Task task);

        @GET("/task/{title}.json")
        Call<Task> getTask(@Path("title") String title); // could be used for fetching details or checking if item already exists

        @GET("/task/.json")
        Call<Map<String, Task>> getAllTasks(); // note that we'll receive a Map here from firebase with key being the identifier

        @DELETE("/task/{title}.json")
        Call<Task> deleteTask(@Path("title") String title);

    }

}