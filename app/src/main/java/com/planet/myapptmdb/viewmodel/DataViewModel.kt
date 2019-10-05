package com.geo.viewmodel

import androidx.lifecycle.ViewModel
import com.geo.utils.Constants.Companion.APIKEY
import com.geo.utils.Constants.Companion.LAN
import com.planet.myapptmdb.model.MoviesAll
import com.planet.myapptmdb.model.ResultsItem
import com.planet.myapptmdb.model.storage.DataSession
import com.planet.upaxtst.networking.Repository
import com.planet.upaxtst.viewmodel.BaseMutableLiveData

class DataViewModel : ViewModel() {

    var movieMutableData: BaseMutableLiveData<MoviesAll>? = null
    var movieFavMutableData: BaseMutableLiveData<List<ResultsItem>>? = null

    init {
        movieMutableData = BaseMutableLiveData()
        movieFavMutableData = BaseMutableLiveData()
    }

    fun init() {
        val repository = Repository(movieMutableData!!)
        movieMutableData!!.inProgress = "Obteniendo datos del servidor."
        repository.onAttemp(repository.serverApi.listMovies(APIKEY, LAN))
    }

    fun init02() {
        movieFavMutableData!!.value = DataSession.getInstance().moviesSelected
    }
}