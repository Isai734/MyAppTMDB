package com.planet.myapptmdb.ui.main

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
import com.planet.myapptmdb.model.ResultsItem

import com.planet.upaxtst.viewmodel.BaseObserver
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_itemmovie_list.*
import java.util.*

class FavMovieFragment : Fragment(), BaseObserver<List<ResultsItem>> {

    override fun inProgress(info: String) {
        if (progressView == null) progressView = Snackbar.make(list, "", Snackbar.LENGTH_INDEFINITE)
        progressView!!.setText(info).show()
    }

    override fun onError(error: String) {
        if (progressView != null) progressView!!.duration = 1000
        progressView!!.setText(error).show()
    }

    override fun onChanged(t: List<ResultsItem>?) {
        moviAdapter!!.swapDatta(t!!)
        if (progressView != null) progressView!!.dismiss()
    }

    // TODO: Customize parameters
    private var columnCount = 1
    protected var moviesViewModel: DataViewModel? = null
    private var moviAdapter: MovieRVAdapter? = null
    private var progressView: Snackbar? = null

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
        moviAdapter = MovieRVAdapter(LinkedList<ResultsItem>(), null)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesViewModel = ViewModelProviders.of(activity!!)[DataViewModel::class.java]
        moviesViewModel!!.movieFavMutableData!!.observe(this, this)
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
