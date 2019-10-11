package com.planet.myapptmdb.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.planet.myapptmdb.model.entities.Person
import androidx.room.RoomDatabase

@Database(entities = [Person::class], version = 1)
abstract class PersonsDatabase : RoomDatabase() {

    abstract fun databaseDao(): DatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: PersonsDatabase? = null

        fun getDatabase(context: Context): PersonsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, PersonsDatabase::class.java, "persons_database").build()
                INSTANCE = instance
                return instance
            }
        }
    }

}