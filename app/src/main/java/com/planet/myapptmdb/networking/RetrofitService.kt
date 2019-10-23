package com.planet.myapptmdb.networking

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitService {

    private const val BASE_URL: String = "https://api.themoviedb.org/3/"

    private val retrofit: Retrofit
        get() = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(MoshiConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }

}
