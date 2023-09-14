package com.example.retrofitexample

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @GET("/api/users/{id}")
    suspend fun getUserByID(@Path("id")id:String):Response<BaseResponse<UserResponse>>

    @PUT("/api/users/{id}")
    suspend fun updateUser(@Path("id") id:String,@Body body: JsonObject):Response<JsonObject>

    @DELETE("/api/users/{id}")
    suspend fun deleteUser(@Path("id") id:String):Response<JsonObject>

    @POST("/api/users/{id}")
    suspend fun createUser(@Body body: JsonObject):Response<JsonObject>


}