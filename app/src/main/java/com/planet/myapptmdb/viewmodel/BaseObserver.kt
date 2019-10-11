package com.planet.myapptmdb.viewmodel

import androidx.lifecycle.Observer

interface BaseObserver<T> : Observer<T> {
    fun inProgress(info: String)
    fun onError(error: String)
}
