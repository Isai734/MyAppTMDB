package com.planet.myapptmdb.model.storage;

import com.planet.myapptmdb.model.ResultsItem;

import java.util.LinkedList;
import java.util.List;

/**
 * Clase que almacena datos persistentes.
 */

public class DataSession {

    private final static String TAG = DataSession.class.getSimpleName();
    private static DataSession ourInstance = null;

    private List<ResultsItem> moviesSelected;

    private DataSession() {
        moviesSelected = new LinkedList<>();
    }

    public static DataSession getInstance() {
        if (ourInstance == null)
            ourInstance = new DataSession();
        return ourInstance;
    }

    public List<ResultsItem> getMoviesSelected() {
        return moviesSelected;
    }

    public void setMoviesSelected(List<ResultsItem> moviesSelected) {
        this.moviesSelected = moviesSelected;
    }
}
