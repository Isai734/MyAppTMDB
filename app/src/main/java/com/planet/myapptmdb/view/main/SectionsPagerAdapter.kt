package com.planet.myapptmdb.view.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.planet.myapptmdb.R

private val TAB_TITLES = arrayOf(R.string.tab_text_1, R.string.tab_text_2)

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return ItemMovieFragment.newInstance(2)
        }
        return FavMovieFragment.newInstance(2)
    }

    override fun getPageTitle(position: Int): CharSequence = context.resources.getString(TAB_TITLES[position])

    override fun getCount() = 2
}