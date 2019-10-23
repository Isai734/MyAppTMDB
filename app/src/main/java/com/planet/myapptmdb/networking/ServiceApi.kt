package com.planet.myapptmdb.networking

import com.planet.myapptmdb.model.MoviesAll
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {
    @GET("movie/popular")
    fun listMovies(@Query("api_key") key: String, @Query("language") language: String): Observable<MoviesAll>
}
