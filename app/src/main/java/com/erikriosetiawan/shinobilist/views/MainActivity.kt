package com.erikriosetiawan.shinobilist.views

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.recyclerViewShinobi.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = ShinobiAdapter(this@MainActivity, SampleData.getShinobis)
        }
    }
}
