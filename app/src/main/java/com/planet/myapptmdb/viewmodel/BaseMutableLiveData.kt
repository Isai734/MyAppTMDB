package com.planet.myapptmdb.viewmodel

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class BaseMutableLiveData<T> : MutableLiveData<T>() {
    private var baseObserver: BaseObserver<in T>? = null

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (observer !is BaseObserver) throw RuntimeException("Observer must instance Of BaseObserver")
        baseObserver = observer
        super.observe(owner, observer)
    }

    var error = ""
        set(value) {
            if (baseObserver != null)
                baseObserver!!.onError(value)
            else
                Log.e(TAG, "BaseObserver must be assigned from view")
        }

    var inProgress = ""
        set(value) {
            if (baseObserver != null)
                baseObserver!!.inProgress(value)
            else
                Log.e(TAG, "BaseObserver must be assigned from view")
        }

    companion object {
        private val TAG = BaseMutableLiveData::class.java.simpleName
    }
}