package com.erikriosetiawan.shinobilist.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.erikriosetiawan.shinobilist.R
import com.erikriosetiawan.shinobilist.adapters.ShinobiAdapter
import com.erikriosetiawan.shinobilist.data.SampleData
import com.erikriosetiawan.shinobilist.database.ShinobiDatabase
import com.erikriosetiawan.shinobilist.databinding.ActivityMainBinding
import com.erikriosetiawan.shinobilist.models.Shinobi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var adapter: ShinobiAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        GlobalScope.launch {
            val database = ShinobiDatabase.getInstace(this@MainActivity).shinobiDatabaseDao
            val myData = database.getAllShinobi()
            runOnUiThread {
                adapter = ShinobiAdapter(this@MainActivity, myData as MutableList<Shinobi>)
            }
        }
        binding.recyclerViewShinobi.layoutManager =
            LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewShinobi.adapter = adapter

        binding.floatingActionButtonAdd.setOnClickListener {
            addShinobi()
        }

    }

    private fun addShinobi() {
        val intent = Intent(this, DetailsActivity::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        adapter?.notifyDataSetChanged()
        for (i in 0 until SampleData.getShinobis.size) {
            val shinobi = SampleData.getShinobis[i]
            Log.d(
                "MainActivity",
                "${shinobi.id}\n${shinobi.name}\n${shinobi.village}\n${shinobi.description}"
            )
        }
    }
}
