package com.planet.upaxtst.networking

import com.planet.myapptmdb.model.MoviesAll
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServerApi {
    @GET("movie/popular")
    fun listMovies(@Query("api_key") key: String, @Query("language") language: String): Call<MoviesAll>
}
