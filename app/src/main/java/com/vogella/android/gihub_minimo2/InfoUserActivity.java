package com.vogella.android.gihub_minimo2;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;


public class InfoUserActivity extends AppCompatActivity {
    TextView repos;
    TextView following;
    TextView userTV;
    RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public static GithubAPI gitAPI;
    private List<Followers> followersList = new ArrayList<>();
    private String userName;
    private User user;
    ImageView userImage;

    private void recibirDatos(){
        Bundle extras = getIntent().getExtras();
        userName = extras.getString("username");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_user);
        recibirDatos();
        userTV = (TextView) findViewById(R.id.usrName_txt2);
        repos = (TextView) findViewById(R.id.numRepo_txt);
        following = (TextView) findViewById(R.id.numFollowing_txt);
        userImage = (ImageView)findViewById(R.id.userImage);
        userTV.setText(userName);
        final LoadingDialog loadingDialog = new LoadingDialog(InfoUserActivity.this);
        loadingDialog.startLoadingDialog();

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(gitAPI.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        gitAPI = retrofit.create(GithubAPI.class);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        Call<User> userCall = gitAPI.getUser(userName);

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user = response.body();
                following.setText(user.getFollowing());
                repos.setText(user.getPublic_repos());
                Glide.with(getApplicationContext()).load(user.getAvatar_url()).into(userImage);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.i("ListFollowers", "Failure " + t.getMessage());
            }
        });

        Call<List<Followers>> followers = (Call<List<Followers>>) gitAPI.getFollowers(userName);

        followers.enqueue(new Callback<List<Followers>>() {
            @Override
            public void onResponse(Call<List<Followers>> call, Response<List<Followers>> response) {
                followersList = response.body();
                if (followersList.size() !=0){
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loadingDialog.dismissDialog();
                        }
                    },20);
                }
                mAdapter = new FollowersAdapter(followersList, getApplicationContext());
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<Followers>> call, Throwable t) {
                Log.i("ListFollowers", "Failure " + t.getMessage());
            }
        });




    }


}
