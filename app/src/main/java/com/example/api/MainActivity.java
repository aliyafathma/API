package com.example.api;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.api.adapter.ItemAdapter;
import com.example.api.databinding.ActivityMainBinding;
import com.example.api.model.Example;
import com.example.api.model.Name;
import com.example.api.model.Result;
import com.example.api.model.User;
import com.example.api.model.UserResponse;
import com.example.api.model.UserkuResponse;
import com.example.api.network.ApiClient;
import com.example.api.network.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ItemAdapter adapter;
    ActivityMainBinding binding;

    private final String TAG = MainActivity.class.getSimpleName();

    private void initList() {
        adapter = new ItemAdapter(new ArrayList<User>());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        this.binding.mainRcvItem.setLayoutManager(layoutManager);
        this.binding.mainRcvItem.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        setContentView(R.layout.activity_main);

        //adapter.add(new Name("Ms","Aliya", "Najihati"));
        initList();

        /*buat manggil Toast 200 (consume API) itu*/
        loadUserAsync(20);
        createUserku();
    }

    private void loadUserAsync(int result) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
//        Call<UserResponse> call = apiInterface.getTopRatedMovies(result);
        Call<UserResponse> call = apiInterface.getUser(result);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse userResponse = response.body();
                showData(userResponse);
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.d(TAG, t.toString());
            }
        });
    }

    private void showData(UserResponse userResponse) {
        adapter = new ItemAdapter(userResponse.getUsers());
        binding.setAdapter(adapter);
    }

    /*Line 82 - 99 adalah fungsi munculin Toast 200*/
    private void createUserku() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<UserkuResponse> call = apiInterface.postUserku("aliya.fathma13@gmail.com.com", "aliya",
                "najihati", "female");
        call.enqueue(new Callback<UserkuResponse>() {
            @Override
            public void onResponse(Call<UserkuResponse> call, Response<UserkuResponse> response) {
                UserkuResponse userkuResponse = response.body();
                Toast.makeText(MainActivity.this, String.valueOf(response.code()),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UserkuResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

