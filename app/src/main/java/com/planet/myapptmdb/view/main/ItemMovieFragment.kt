package com.planet.myapptmdb.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.planet.myapptmdb.viewmodel.DataViewModel
import com.google.android.material.snackbar.Snackbar
import com.planet.myapptmdb.R
import com.planet.myapptmdb.model.MoviesAll
import com.planet.myapptmdb.model.ResultsItem
import com.planet.myapptmdb.utils.OnListInteractionListener

import com.planet.myapptmdb.viewmodel.BaseObserver
import kotlinx.android.synthetic.main.fragment_itemmovie_list.*
import java.util.*

open class ItemMovieFragment : Fragment(), BaseObserver<MoviesAll>,
    OnListInteractionListener<ResultsItem?> {

    override fun onStart() {
        super.onStart()
        moviesViewModel = activity?.run {
            ViewModelProviders.of(this)[DataViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
        moviesViewModel!!.movieMutableData!!.observe(this, this)
        moviesViewModel!!.getMovies()
    }

    override fun onListClickItem(item: ResultsItem?) {
        moviesViewModel!!.getFavMovies()
    }

    override fun inProgress(info: String) {
        if (progressView == null) progressView = Snackbar.make(list, "", Snackbar.LENGTH_INDEFINITE)
        progressView!!.setText(info).show()
    }

    override fun onError(error: String) {
        if (progressView != null) {
            progressView!!.duration = 3000
            progressView!!.setText(error).show()
        }
    }

    override fun onChanged(t: MoviesAll?) {
        moviAdapter!!.swapData(t!!.results)
        if (progressView != null) progressView!!.dismiss()
    }

    private var columnCount = 1
    private var moviesViewModel: DataViewModel? = null
    private var moviAdapter: MovieRVAdapter? = null
    private var progressView: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_itemmovie_list, container, false)
        moviAdapter = MovieRVAdapter(LinkedList(), this)
        // Set the movieRVAdapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = moviAdapter
            }
        }
        return view
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"
        @JvmStatic
        fun newInstance(columnCount: Int) =
            ItemMovieFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
