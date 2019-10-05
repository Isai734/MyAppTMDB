package com.planet.myapptmdb

import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.geo.BaseActivity
import com.geo.viewmodel.DataViewModel
import com.planet.myapptmdb.model.MoviesAll
import com.planet.myapptmdb.model.ResultsItem
import com.planet.myapptmdb.model.storage.DataSession
import com.planet.myapptmdb.ui.main.FavMovieFragment
import com.planet.myapptmdb.ui.main.ItemMovieFragment
import com.planet.myapptmdb.ui.main.SectionsPagerAdapter
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.lifecycle.ViewModelProviders
import com.planet.myapptmdb.utils.OnListFragmentInteractionListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
    }
}