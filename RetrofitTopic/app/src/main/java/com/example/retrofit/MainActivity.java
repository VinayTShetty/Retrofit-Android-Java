package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.retrofit.PojoCreation.Comment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView responseTextview;
    JsonPlaceHolderApi jsonPlaceHolderApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        responseTextview=(TextView)findViewById(R.id.responseTextview_id);
        retrofitIntialization();
        getRequestFromURL();
    }

    private void retrofitIntialization(){
        /**
         * Document Explanation for the Steps why this process is required.
         */
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi.class);
    }

    private void RetrofitCallFromURL(){
      Call<List<Comment>> callresponse= jsonPlaceHolderApi.getCommentsRemoveHardCodingURL(10);
      callresponse.enqueue(new Callback<List<Comment>>() {
          @Override
          public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
              if(!response.isSuccessful()){
                  responseTextview.setText(response.code());
                  return;
              }
              List<Comment> comments=  response.body();
              for(Comment comment:comments){
                  String content="";
                  content+="ID= "+comment.getId()+"\n";
                  content+="Post ID= "+comment.getPostId()+"\n";
                  content+="Name = "+comment.getName()+"\n";
                  content+="Email = "+comment.getEmail()+"\n";
                  content+="Text= "+comment.getText()+"\n"+"\n";
                  responseTextview.append(content);
              }
          }

          @Override
          public void onFailure(Call<List<Comment>> call, Throwable t) {

          }
      });
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

    private void getCommentsFromQuerey(){
        Call<List<Comment>> callresponse= jsonPlaceHolderApi.getCommentsByQuerey(10);
        callresponse.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if(!response.isSuccessful()){
                    responseTextview.setText(response.code());
                    return;
                }
                List<Comment> comments=  response.body();
                for(Comment comment:comments){
                    String content="";
                    content+="Post ID= "+comment.getPostId()+"\n";
                    content+="ID= "+comment.getId()+"\n";
                    content+="Name = "+comment.getName()+"\n";
                    content+="Email = "+comment.getEmail()+"\n";
                    content+="Text= "+comment.getText()+"\n"+"\n";
                    responseTextview.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                responseTextview.setText(t.getMessage());
            }
        });
    }

    private void mulitpleQuereyRetrofit(){
        /**
         * Document Explanation for the Steps why this process is required.
         */
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Post>> call=jsonPlaceHolderApi.getPostByMulitpleQuereyforMultipleUsers(new Integer[]{2,3,10},null,null);
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

    /**
     * Dynamically pass the Querey in Runtime
     */
    private void usingMapQuerey(){
        // In Map we cannot pass mulitple user id like its done using array concepts.
        Map<String,String> map=new HashMap<>();
        map.put("userId","2");
        map.put("_sort","id");
        map.put("_order","desc");
        /**
         * Document Explanation for the Steps why this process is required.
         */
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi=retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Post>> call=jsonPlaceHolderApi.passQuereyDynamicallyUsingMap(map);
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

    /**
     * Notes on Passing the URL.
     *
     * Note 1:- We can pass the compelete url for the parameter including the base URL as below.
     *          https://jsonplaceholder.typicode.com/posts/3/comments
     *
     *          It will overwrite the Base URL that s it.
     *
     * Note 2:- Base URL should always be closed with /
     *  ex 1 :- https://jsonplaceholder.typicode.com/   --->Valid.
     *  ex 2 :- https://jsonplaceholder.typicode.com/v3   --->InValid.(Crash might occur)
     *
     *
     *  Note 3:- Remove the Traiilng / in the interface.
     *  If we add the "/post" in the interface of the API It will remove the previous trailings.
     *
     *  Base URL= https://jsonplaceholder.typicode.com/v3
     *  Pass parameter as "/posts"
     *  Result = https://jsonplaceholder.typicode.com/posts
     *
     *   Base URL= https://jsonplaceholder.typicode.com/v3
     *   Pass parameter as "posts"
     *   Result = https://jsonplaceholder.typicode.com/v3/posts
     */
    private void getRequestFromURL(){
        Call<List<Comment>> callresponse= jsonPlaceHolderApi.getCommentsByQuerey("posts/3/comments");
        callresponse.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if(!response.isSuccessful()){
                    responseTextview.setText(response.code());
                    return;
                }
                List<Comment> comments=  response.body();
                for(Comment comment:comments){
                    String content="";
                    content+="ID= "+comment.getId()+"\n";
                    content+="Post ID= "+comment.getPostId()+"\n";
                    content+="Name = "+comment.getName()+"\n";
                    content+="Email = "+comment.getEmail()+"\n";
                    content+="Text= "+comment.getText()+"\n"+"\n";
                    responseTextview.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

            }
        });
    }
}