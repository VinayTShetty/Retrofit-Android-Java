package com.example.retrofit;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {
    /**
     * As this is a Get request from the Webserver.
     * Annotate it with @GET("posts")
     * posts is the relative layout
     */
    @GET("posts")
    Call<List<Post>> getPost();
}
