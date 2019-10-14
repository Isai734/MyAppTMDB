package com.planet.myapptmdb.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.planet.myapptmdb.R
import com.planet.myapptmdb.model.entities.Person
import com.planet.myapptmdb.utils.OnListInteractionListener
import com.planet.myapptmdb.view.main.PersonRVAdapter
import com.planet.myapptmdb.viewmodel.DataViewModel
import kotlinx.android.synthetic.main.activity_list.*
import java.util.*

class ListActivity : AppCompatActivity(), OnListInteractionListener<Person>,
    Observer<List<Person>> {

    override fun onChanged(t: List<Person>?) {
        personRVAdapter?.swapData(t!!)
    }

    override fun onListClickItem(item: Person) {
        dataViewModel?.insert(item)
    }

    private var personRVAdapter: PersonRVAdapter? = null
    private var dataViewModel: DataViewModel? = null

    override fun onStart() {
        super.onStart()
        dataViewModel = run {
            ViewModelProviders.of(this).get(DataViewModel::class.java)
        }
        dataViewModel!!.getPersons()!!.observe(this, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        supportActionBar ?: supportActionBar?.setDisplayHomeAsUpEnabled(true)
        personRVAdapter = PersonRVAdapter(LinkedList(), this)

        with(list_person) {
            layoutManager = LinearLayoutManager(context)
            adapter = personRVAdapter
        }

        fab_add_person.setOnClickListener {
            val personAddDialog = PersonAddDialog.newInstance()
            personAddDialog.show(supportFragmentManager, personAddDialog.tag)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                this.finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
