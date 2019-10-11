package com.planet.myapptmdb.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.planet.myapptmdb.viewmodel.DataViewModel
import com.planet.myapptmdb.R
import com.planet.myapptmdb.model.ResultsItem
import java.util.*

class FavMovieFragment : Fragment(), Observer<List<ResultsItem>> {

    private var columnCount = 1
    protected var moviesViewModel: DataViewModel? = null
    private var moviAdapter: MovieRVAdapter? = null

    override fun onStart() {
        super.onStart()
        moviesViewModel =
            activity?.run { ViewModelProviders.of(activity!!)[DataViewModel::class.java] }
                ?: throw Exception("Invalid Activity")
        moviesViewModel!!.movieFavMutableData!!.observe(this, this)
    }

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

    override fun onChanged(t: List<ResultsItem>?) {
        moviAdapter!!.swapData(t!!)
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"
        @JvmStatic
        fun newInstance(columnCount: Int) =
            FavMovieFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
