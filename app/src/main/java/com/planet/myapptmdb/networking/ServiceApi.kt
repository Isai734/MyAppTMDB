package com.planet.myapptmdb.networking

import com.planet.myapptmdb.model.MoviesAll
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {
    @GET("movie/popular")
    suspend fun listMovies(@Query("api_key") key: String, @Query("language") language: String): Response<MoviesAll>
}
