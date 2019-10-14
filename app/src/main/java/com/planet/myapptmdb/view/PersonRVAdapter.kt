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
import com.planet.myapptmdb.model.entities.Person
import com.planet.myapptmdb.utils.DataSession


import com.planet.myapptmdb.utils.OnListInteractionListener

import kotlinx.android.synthetic.main.item_movie.view.*
import kotlinx.android.synthetic.main.item_person.view.*

class PersonRVAdapter(
    private var mValues: List<Person>,
    private val mListener: OnListInteractionListener<Person>?
) : RecyclerView.Adapter<PersonRVAdapter.ViewHolder>() {

    fun swapData(mValues: List<Person>) {
        this.mValues = mValues
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_person, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mNamePerson.text = item.name
        holder.mAgePerson.text = item.age.toString()
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mNamePerson: TextView = mView.txv_name_person
        val mAgePerson: TextView = mView.txv_age_person
        override fun toString(): String {
            return super.toString() + " '" + mNamePerson.text + "'"
        }
    }
}
