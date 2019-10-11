package com.planet.myapptmdb.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.planet.myapptmdb.R
import com.planet.myapptmdb.view.main.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        view_pager.adapter = SectionsPagerAdapter(this, supportFragmentManager)
        tabs.setupWithViewPager(view_pager)
    }
}