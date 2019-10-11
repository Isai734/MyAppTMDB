package com.planet.myapptmdb.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.planet.myapptmdb.model.entities.Person

@Dao
interface DatabaseDao {
    @Query("select * from person")
    fun getAllPersons(): LiveData<List<Person>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPerson(person: Person)
}