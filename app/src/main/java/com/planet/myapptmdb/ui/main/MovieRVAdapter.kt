package com.planet.myapptmdb.ui.main

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.geo.utils.Constants.Companion.BASE_URL_IMAGES
import com.planet.myapptmdb.R
import com.planet.myapptmdb.model.ResultsItem
import com.planet.myapptmdb.model.storage.DataSession


import com.planet.myapptmdb.utils.OnListFragmentInteractionListener

import kotlinx.android.synthetic.main.item_movie.view.*

/**
 * [RecyclerView.Adapter] that can display a [ResultsItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MovieRVAdapter(
    private var mValues: List<ResultsItem>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MovieRVAdapter.ViewHolder>() {

    val dataSession = DataSession.getInstance()

    fun swapDatta(mValues: List<ResultsItem>) {
        this.mValues = mValues
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        Glide.with(holder.mView).load(BASE_URL_IMAGES + item.posterPath)
            .placeholder(R.drawable.avatar_backgrpund).into(holder.mPosterView)
        holder.mNameMovie.text = item.originalTitle
        if (mListener == null) holder.select.visibility = View.GONE
        holder.select.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked)
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
