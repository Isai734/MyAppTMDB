package com.planet.myapptmdb.ui.main

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.geo.utils.Constants.Companion.BASE_URL_IMAGES
import com.planet.myapptmdb.R
import com.planet.myapptmdb.model.ResultsItem
import com.planet.myapptmdb.model.storage.DataSession


import com.planet.myapptmdb.ui.main.ItemMovieFragment.OnListFragmentInteractionListener
import com.planet.myapptmdb.ui.main.dummy.DummyContent.DummyItem

import kotlinx.android.synthetic.main.fragment_itemmovie.view.*
import java.security.AccessController.getContext

/**
 * [RecyclerView.Adapter] that can display a [ResultsItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MovieRVAdapter(
    private var mValues: List<ResultsItem>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MovieRVAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener
    val dataSession = DataSession.getInstance()

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as ResultsItem
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    fun swapDatta(mValues: List<ResultsItem>) {
        this.mValues = mValues
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_itemmovie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        Glide.with(holder.mView).load(BASE_URL_IMAGES + item.posterPath)
            .placeholder(R.drawable.avatar_backgrpund).into(holder.mPosterView)
        holder.mNameMovie.text = item.originalTitle
        holder.select.setOnClickListener(View.OnClickListener {
            if (holder.select.isSelected)
                dataSession.moviesSelected.add(item)
            else
                dataSession.moviesSelected.remove(item)
            mListener!!.onListFragmentInteraction(item)
        })
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mPosterView: ImageView = mView.imv_poster
        val mNameMovie: TextView = mView.name_movie
        val select: CheckBox = mView.chk_select
        override fun toString(): String {
            return super.toString() + " '" + mNameMovie.text + "'"
        }
    }
}
