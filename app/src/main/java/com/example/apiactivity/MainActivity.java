package com.example.apiactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
TextView textView;
ApiService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.text);

        apiService=Apiclient.getRetrofit().create(ApiService.class);
        Call<ArrayList<ApiModel>> calllist=apiService.getpostList();
        calllist.enqueue(new Callback<ArrayList<ApiModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ApiModel>> call, Response<ArrayList<ApiModel>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "error"+response.code(), Toast.LENGTH_SHORT).show();
             return;   }
                StringBuilder stringBuilder=new StringBuilder();
                for(ApiModel apiModel:response.body()){
                    stringBuilder.append("id =>"+apiModel.getId()+"\n");
                    stringBuilder.append("userId =>"+apiModel.getUserId()+"\n");
                    stringBuilder.append("title =>"+apiModel.getTitle()+"\n");
                    stringBuilder.append("body =>"+apiModel.getBody()+"\n");


                }
                textView.setText(stringBuilder);
            }

            @Override
            public void onFailure(Call<ArrayList<ApiModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}