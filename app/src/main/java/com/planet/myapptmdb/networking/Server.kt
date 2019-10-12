package com.planet.myapptmdb.networking

import android.util.Log
import com.planet.myapptmdb.model.ResponseErr
import com.planet.myapptmdb.viewmodel.BaseMutableLiveData
import retrofit2.HttpException
import retrofit2.Response

class Server<E>(private val mutableData: BaseMutableLiveData<E>) {

    fun onAttempt(response: Response<E>?): BaseMutableLiveData<E> {
        try {
            if (response!!.isSuccessful) {
                mutableData.value = response.body()
            } else {
                try {
                    if (response.errorBody()?.contentType()?.subtype() == "json") {
                        val body = response.errorBody()!!.string()
                        val error = ResponseErr.fromResponseBody(body)
                        mutableData.error = error?.statusMessage!!
                    } else
                        mutableData.error = response.message()
                } catch (e: Exception) {
                    mutableData.error = "No se puede conectar al servidor."
                }
            }
        } catch (e: HttpException) {
            mutableData.error = e.message!!
        }
        return mutableData
    }

    companion object {
        private var TAG = Server::class.java.simpleName
    }
}