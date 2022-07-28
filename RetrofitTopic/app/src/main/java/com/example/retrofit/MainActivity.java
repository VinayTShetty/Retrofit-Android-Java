package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView responseTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        responseTextview=(TextView)findViewById(R.id.responseTextview_id);
        createRetrofitInstnace();
    }

    private void createRetrofitInstnace(){
        /**
         * Document Explanation for the Steps why this process is required.
         */
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Post>> call=jsonPlaceHolderApi.getPost();
        /**
         * call.enqueue will by default run on the background Thread.
         */
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    responseTextview.setText(response.code());
                    return;
                }
              List<Post> posts=  response.body();
                for(Post post:posts){
                    String content="";
                    content+="ID= "+post.getId()+"\n";
                    content+="User ID= "+post.getUserId()+"\n";
                    content+="Title = "+post.getTitle()+"\n";
                    content+="Text = "+post.getText()+"\n"+"\n";
                    responseTextview.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                responseTextview.setText(t.getMessage());
            }
        });

    }
}