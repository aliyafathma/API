package com.example.api.network;

import com.example.api.model.Example;
import com.example.api.model.UserResponse;
import com.example.api.model.UserkuResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("api/")
    /*Line 18 buat nampilin yang kita bind (title, first name, last name)*/
    //Call<UserResponse> getTopRatedMovies(@Query("results") int result);

    /*Line 21-28 untuk yang Consume API*/
    Call<UserResponse>getUser(@Query("results") int result);

    @Headers("Authorization: Bearer afPOPQbZk2HaEK7RBxLEDXgISOaAPmyPJUvG")
    @FormUrlEncoded
    @POST("public-api/users")

    Call<UserkuResponse> postUserku(@Field("email") String email, @Field("first_name") String firstName,
                                    @Field("last_name") String lastName, @Field("gender") String gender);
}
