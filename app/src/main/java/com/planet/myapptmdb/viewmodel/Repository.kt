package com.planet.myapptmdb.viewmodel

import android.app.Application
import com.planet.myapptmdb.utils.Constants
import com.planet.myapptmdb.db.DatabaseDao
import com.planet.myapptmdb.db.PersonsDatabase
import com.planet.myapptmdb.model.MoviesAll
import com.planet.myapptmdb.model.entities.Person
import com.planet.myapptmdb.networking.ServiceApi
import com.planet.myapptmdb.networking.RetrofitService
import com.planet.myapptmdb.networking.Server

class Repository(application: Application) {
    private var databaseDao: DatabaseDao? = null
    private var serviceApi: ServiceApi? = null

    init {
        databaseDao = PersonsDatabase.getDatabase(application).databaseDao()
        serviceApi = RetrofitService.createService(ServiceApi::class.java)
    }

    fun getAllPersons() = databaseDao?.getAllPersons()

    fun getAllMovies(movieMutableData: BaseMutableLiveData<MoviesAll>) {
        val repository = Server(movieMutableData)
        repository.onAttempt(serviceApi?.listMovies(Constants.APIKEY, Constants.LAN))
    }

    suspend fun insert(person: Person) = databaseDao?.insertPerson(person)
}
