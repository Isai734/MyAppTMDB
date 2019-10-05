package com.planet.myapptmdb.ui.main

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.geo.viewmodel.DataViewModel
import com.google.android.material.snackbar.Snackbar
import com.planet.myapptmdb.R
import com.planet.myapptmdb.model.MoviesAll
import com.planet.myapptmdb.model.ResultsItem
import com.planet.myapptmdb.model.storage.DataSession

import com.planet.myapptmdb.ui.main.dummy.DummyContent.DummyItem
import com.planet.upaxtst.viewmodel.BaseObserver
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_itemmovie_list.*
import java.util.*

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [FavMovieFragment.OnListFragmentInteractionListener] interface.
 */
class FavMovieFragment : Fragment(), BaseObserver<List<ResultsItem>> {
    override fun inProgress(info: String) {
        if (progressView == null) progressView = Snackbar.make(list, "", Snackbar.LENGTH_INDEFINITE)
        progressView!!.setText(info).show()

    }

    override fun onError(error: String) {
        if (progressView != null) progressView!!.setDuration(Snackbar.LENGTH_LONG)
        progressView!!.setText(error).show()
    }

    override fun onChanged(t: List<ResultsItem>?) {
        moviAdapter!!.swapDatta(t!!)
        if (progressView != null) progressView!!.dismiss()
        Log.d("FavMovieFragment", "Hola estoy onChanged")
    }

    // TODO: Customize parameters
    private var columnCount = 1
    protected var moviesViewModel: DataViewModel? = null
    private var moviAdapter: MovieRVAdapter? = null
    private var progressView: Snackbar? = null
    private var recyclerView: RecyclerView? = null

    private var listener: ItemMovieFragment.OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_itemmovie_list, container, false)
        recyclerView = view as RecyclerView
        moviAdapter = MovieRVAdapter(LinkedList<ResultsItem>(), listener)
        /*/ Set the movieRVAdapter
         if (view is RecyclerView) {
             with(view) {
                 layoutManager = when {
                     columnCount <= 1 -> LinearLayoutManager(context)
                     else -> GridLayoutManager(context, columnCount)
                 }
                 adapter = moviAdapter
             }
         }*/
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ItemMovieFragment.OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onResume() {
        super.onResume()
        moviAdapter!!.swapDatta(DataSession.getInstance().moviesSelected)
        Log.d("FavMovieFragment", "Hola estoy onResume: "+DataSession.getInstance().moviesSelected.size)
    }

    fun setListMovies(mValues: List<ResultsItem>?) {
        /* moviAdapter = MovieRVAdapter(mValues!!, listener)
         recyclerView!!.layoutManager = GridLayoutManager(context, columnCount)
         recyclerView!!.adapter = moviAdapter*/
        if (mValues != null)
            moviAdapter!!.swapDatta(mValues)

        /*moviesViewModel = ViewModelProviders.of(this).get(DataViewModel::class.java)
        moviesViewModel!!.movieFavMutableData!!.observe(this, this)
        moviesViewModel!!.init02()*/
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            FavMovieFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
