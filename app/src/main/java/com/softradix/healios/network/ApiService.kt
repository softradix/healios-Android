package com.softradix.healios.network

import com.softradix.healios.model.Comments
import com.softradix.healios.model.Posts
import com.softradix.healios.model.user.Users
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

sealed interface ApiService {

    @GET("comments")
    fun getComments(): Call<List<Comments>>

    @GET("users")
    fun getUsers(): Call<List<Users>>

    @GET("posts")
    fun getPosts(): Call<List<Posts>>

}