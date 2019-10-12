package com.planet.myapptmdb.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.planet.myapptmdb.model.MoviesAll
import com.planet.myapptmdb.model.entities.Person
import com.planet.myapptmdb.model.ResultsItem
import com.planet.myapptmdb.utils.DataSession
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DataViewModel(application: Application) : AndroidViewModel(application) {

    var movieMutableData: BaseMutableLiveData<MoviesAll>? = null
    var movieFavMutableData: MutableLiveData<List<ResultsItem>>? = null
    private var repository: Repository? = null

    init {
        repository = Repository(application)
        movieMutableData = BaseMutableLiveData()
        movieFavMutableData = MutableLiveData()
    }

    fun getMovies() {
        movieMutableData?.inProgress = "Obteniendo datos del servidor."
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                repository?.getAllMovies(movieMutableData!!)
            }
        }
    }

    fun getFavMovies() {
        movieFavMutableData?.value = DataSession.instance.moviesSelected
    }

    fun getPersons() = repository?.getAllPersons()

    fun insert(person: Person) = viewModelScope.launch {
        repository?.insert(person)
    }
}