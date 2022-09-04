package com.example.retrofit;

import com.example.retrofit.PojoCreation.Comment;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {
    /**
     * As this is a Get request from the Webserver.
     * Annotate it with @GET("posts")
     * posts is the relative layout
     */
    @GET("posts")
    Call<List<Post>> getPost();

    /**
     *  Final URL for the Response
     *  https://jsonplaceholder.typicode.com/posts/2/comments
     */
    @GET("posts/2/comments")
    Call<List<Comment>> getComments();

    /**
     *  Final URL for the Response
     *  https://jsonplaceholder.typicode.com/posts/2/comments
     *  Removing the HardCoding of the URL
     *  Note:- posts/{id}/comments
     *         @Path("id")
     *         Here both id should be case sensitive
     */
    @GET("posts/{id}/comments")
    Call<List<Comment>> getCommentsRemoveHardCodingURL(@Path("id") int postId);
}
