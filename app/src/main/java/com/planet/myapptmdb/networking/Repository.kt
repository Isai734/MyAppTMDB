package com.planet.upaxtst.networking

import android.util.Log
import com.planet.myapptmdb.model.ResponseError
import com.planet.upaxtst.viewmodel.BaseMutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository<E>(val mutableData: BaseMutableLiveData<E>) : Callback<E> {

    val serverApi: ServerApi = RetrofitService.createService(ServerApi::class.java)

    fun onAttemp(call: Call<E>): BaseMutableLiveData<E> {
        call.enqueue(this)
        return mutableData
    }

    override fun onResponse(call: Call<E>, response: Response<E>) {
        Log.d(TAG, "onResponse 01: ${response.message()}")
        if (!response.isSuccessful) {
            try {
                if (response.errorBody()!!.contentType()!!.subtype() == "json") {
                    val error = ResponseError.fromResponseBody(response.errorBody()!!.string())
                    mutableData.error = error!!.statusMessage!!
                } else
                    mutableData.error = response.message()
            } catch (e: Exception) {
                mutableData.error = response.message()
            }
        } else
            mutableData.value = response.body()
    }

    override fun onFailure(call: Call<E>, t: Throwable) {
        Log.d(TAG, "onFailure 02: " + t.message)
        mutableData.error = t.message!!
    }

    companion object {
        private var TAG = Repository::class.java.simpleName
    }
}