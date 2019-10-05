package com.geo

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.geo.viewmodel.DataViewModel
import com.google.android.material.snackbar.Snackbar
import com.planet.myapptmdb.model.ResponseError
import com.planet.upaxtst.viewmodel.BaseObserver
import kotlinx.android.synthetic.main.activity_main.*

abstract class BaseActivity<T> : AppCompatActivity(), BaseObserver<T, ResponseError> {

    private var progressView: Snackbar? = null
    protected var moviesViewModel: DataViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

    }

    override fun inProgress(info: String) {
        if (progressView == null) progressView = Snackbar.make(fab, "", Snackbar.LENGTH_INDEFINITE)
        progressView!!.setText(info).show()
    }

    fun <S : ViewModel> factoryViewModel(serviceClass: Class<S>): S {
        return ViewModelProviders.of(this).get(serviceClass)
    }
}
