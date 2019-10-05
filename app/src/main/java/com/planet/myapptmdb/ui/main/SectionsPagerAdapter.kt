package com.planet.myapptmdb.ui.main

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.planet.myapptmdb.R
import com.planet.myapptmdb.model.ResultsItem
import java.util.*

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    private var frag: FavMovieFragment? = null

    override fun getItem(position: Int): Fragment {
        Log.d("FavMovieFragment", "Hola estoy $position")
        when (position) {
            0 ->
                return ItemMovieFragment.newInstance(2)
        }
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        frag = FavMovieFragment.newInstance(2)
        return frag as FavMovieFragment
    }

    override fun notifyDataSetChanged() {
        frag!!.setListMovies(LinkedList<ResultsItem>())
        super.notifyDataSetChanged()

        Log.d("FavMovieFragment", "Hola estoy notifyDataSetChanged")
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}