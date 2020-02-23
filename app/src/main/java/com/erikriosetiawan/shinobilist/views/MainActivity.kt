package com.erikriosetiawan.shinobilist.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.erikriosetiawan.shinobilist.R
import com.erikriosetiawan.shinobilist.adapters.ShinobiAdapter
import com.erikriosetiawan.shinobilist.data.SampleData
import com.erikriosetiawan.shinobilist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ShinobiAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        adapter = ShinobiAdapter(this, SampleData.getShinobis)
        binding.recyclerViewShinobi.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = adapter
        }

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
        adapter.notifyDataSetChanged()
    }
}
