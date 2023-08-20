package ru.skillbranch.gameofthrones.network

import retrofit2.Call
import retrofit2.http.*


interface JSONPlaceHolderApi {
    @GET("/posts/{id}")
    fun getPostWithID(@Path("id") id: Int): Call<Post?>?

    @GET("/api/houses/{id}")
    fun getHouseWithID(@Path("id") id: Int): Call<House?>?

    @GET("/api/characters/{id}")
    fun getCharacterByID(@Path("id") id: Int): Call<Character?>?
}