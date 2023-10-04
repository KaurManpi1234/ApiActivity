package com.example.apiactivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
  @GET("posts")


    Call<ArrayList<ApiModel>>getpostList();







}
