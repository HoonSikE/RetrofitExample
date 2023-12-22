package com.example.retrofitexample.Retrofit

import retrofit2.Call
import retrofit2.http.*

interface JsonPlaceholderService {
    // 단일
    @GET("posts/{id}")
    fun getPost(@Path("id") postId: Int): Call<Post>

    // 게시물 목록
    @GET("posts")
    fun getPostList(): Call<List<Post>>

    @POST("posts")
    fun createPost(@Body post: Post): Call<Post>

    @PUT("posts/{id}")
    fun updatePost(@Path("id") postId: Int, @Body post: Post): Call<Post>

    @DELETE("posts/{id}")
    fun deletePost(@Path("id") postId: Int): Call<Void>
}