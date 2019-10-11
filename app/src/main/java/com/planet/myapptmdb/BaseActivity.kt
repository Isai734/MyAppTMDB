package com.geo

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.planet.myapptmdb.viewmodel.DataViewModel
import com.google.android.material.snackbar.Snackbar
import com.planet.myapptmdb.viewmodel.BaseObserver
import kotlinx.android.synthetic.main.fragment_itemmovie_list.*

abstract class BaseActivity<T> : AppCompatActivity(), BaseObserver<T> {

    private var progressView: Snackbar? = null
    protected var moviesViewModel: DataViewModel? = null

    override fun inProgress(info: String) {
        if (progressView == null) progressView = Snackbar.make(list, "", Snackbar.LENGTH_INDEFINITE)
        progressView!!.setText(info).show()
    }

    fun <S : ViewModel> factoryViewModel(serviceClass: Class<S>): S {
        return ViewModelProviders.of(this).get(serviceClass)
    }
}
