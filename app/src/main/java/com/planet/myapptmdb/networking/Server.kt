package com.planet.myapptmdb.networking

import android.util.Log
import com.planet.myapptmdb.model.ResponseError
import com.planet.myapptmdb.viewmodel.BaseMutableLiveData
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Server<E>(private val mutableData: BaseMutableLiveData<E>) : Observer<E> {

    override fun onComplete() {
        Log.w(TAG, "onNext: ")
    }

    override fun onSubscribe(d: Disposable) {

    }

    override fun onNext(t: E) {
        mutableData.value = t
    }

    override fun onError(t: Throwable) {
        Log.w(TAG, "onFailure: ${t.message}")
        mutableData.error = t.message!!
    }

    fun onAttempt(disposable: Observable<E>?): BaseMutableLiveData<E> {
        disposable!!.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this)
        return mutableData
    }

    companion object {
        private var TAG = Server::class.java.simpleName
    }
}