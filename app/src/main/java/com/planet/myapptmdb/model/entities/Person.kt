package com.planet.myapptmdb.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person")
class Person constructor(@PrimaryKey @ColumnInfo(name = "name") val name: String, val age: Int)