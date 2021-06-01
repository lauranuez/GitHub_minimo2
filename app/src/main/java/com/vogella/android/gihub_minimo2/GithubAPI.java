package com.vogella.android.gihub_minimo2;
import java.util.List;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface GithubAPI {
    String ENDPOINT = "https://api.github.com";

    @GET("/users/{owner}")
    Call<User> getUser(@Path("owner") String owner);

    @GET("/users/{owner}/followers")
    Call<List<Followers>> getFollowers(@Path("owner") String owner);
}

