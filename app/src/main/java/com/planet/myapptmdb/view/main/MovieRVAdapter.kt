package com.planet.myapptmdb.view.main

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.planet.myapptmdb.utils.Constants.BASE_URL_IMAGES
import com.planet.myapptmdb.R
import com.planet.myapptmdb.model.ResultsItem
import com.planet.myapptmdb.utils.DataSession


import com.planet.myapptmdb.utils.OnListInteractionListener

import kotlinx.android.synthetic.main.item_movie.view.*

class MovieRVAdapter(
    private var mValues: List<ResultsItem>,
    private val mListener: OnListInteractionListener<ResultsItem?>?
) : RecyclerView.Adapter<MovieRVAdapter.ViewHolder>() {

    private val dataSession = DataSession.instance

    fun swapData(mValues: List<ResultsItem>) {
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
        holder.select.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked)
                dataSession.moviesSelected!!.add(item)
            else
                dataSession.moviesSelected!!.remove(item)
            mListener!!.onListClickItem(item)
        }
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
