package com.planet.myapptmdb.utils

import com.planet.myapptmdb.model.ResultsItem

import java.util.LinkedList

/**
 * Clase que almacena datos persistentes.
 */

class DataSession private constructor() {

    var moviesSelected: MutableList<ResultsItem>? = null

    init {
        moviesSelected = LinkedList()
    }

    companion object {

        private val TAG = DataSession::class.java.simpleName
        private var ourInstance: DataSession? = null

        val instance: DataSession
            get() {
                if (ourInstance == null)
                    ourInstance =
                        DataSession()
                return ourInstance as DataSession
            }
    }
}
