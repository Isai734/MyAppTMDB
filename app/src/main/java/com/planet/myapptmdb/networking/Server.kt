package com.planet.myapptmdb.networking

import android.util.Log
import com.planet.myapptmdb.model.ResponseError
import com.planet.myapptmdb.viewmodel.BaseMutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Server<E>(private val mutableData: BaseMutableLiveData<E>) : Callback<E> {

    fun onAttempt(call: Call<E>?): BaseMutableLiveData<E> {
        call?.enqueue(this)
        return mutableData
    }

    override fun onResponse(call: Call<E>, response: Response<E>) {
        Log.d(TAG, "onResponse: ${response.message()}")
        if (!response.isSuccessful) {
            try {
                if (response.errorBody()?.contentType()?.subtype() == "json") {
                    val error = ResponseError.fromResponseBody(response.errorBody()!!.string())
                    mutableData.error = error?.statusMessage!!
                } else
                    mutableData.error = response.message()
            } catch (e: Exception) {
                mutableData.error = "No se puede conectar al servidor."
            }
        } else//Response Successful
            mutableData.value = response.body()
    }

    override fun onFailure(call: Call<E>, t: Throwable) {
        Log.d(TAG, "onFailure: " + t.message)
        mutableData.error = t.message.toString()
    }

    companion object {
        private var TAG = Server::class.java.simpleName
    }
}