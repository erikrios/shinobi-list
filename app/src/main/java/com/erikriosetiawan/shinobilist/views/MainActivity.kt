package com.erikriosetiawan.shinobilist.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.erikriosetiawan.shinobilist.R
import com.erikriosetiawan.shinobilist.adapters.ShinobiAdapter
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

        binding.floatingActionButtonAdd.setOnClickListener {
            addShinobi()
        }
    }

    override fun onStart() {
        super.onStart()
        GlobalScope.launch {
            val database = ShinobiDatabase.getInstace(this@MainActivity).shinobiDatabaseDao
            val myData = database.getAllShinobi()
            runOnUiThread {
                if (myData != null)
                    for (i in 0 until myData.size) {
                        Log.d(
                            "MainActivity",
                            "${myData[i].id}\n${myData[i].name}\n${myData[i].village}\n${myData[i].description}\n\n"
                        )
                    }
                adapter = ShinobiAdapter(this@MainActivity, myData as MutableList<Shinobi>)
                binding.recyclerViewShinobi.layoutManager =
                    LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                binding.recyclerViewShinobi.adapter = adapter
            }
        }
    }

    private fun addShinobi() {
        val intent = Intent(this, DetailsActivity::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        adapter?.notifyDataSetChanged()
    }
}
