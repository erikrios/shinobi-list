package com.erikriosetiawan.shinobilist.views

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.erikriosetiawan.shinobilist.R
import com.erikriosetiawan.shinobilist.data.SampleData
import com.erikriosetiawan.shinobilist.database.ShinobiDatabase
import com.erikriosetiawan.shinobilist.databinding.ActivityDetailsBinding
import com.erikriosetiawan.shinobilist.models.Shinobi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    companion object {
        const val DATA_SHINOBI_KEY = "data_shinobi_key"
        const val DATA_SHINOBI_TYPE = "data_shinobi_type"
    }

    private var shinobi: Shinobi? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        binding.buttonSave.setOnClickListener {
            if (binding.buttonSave.text == getString(R.string.save)) {
                addNewData()
                finish()
            } else {
                val shinobi = shinobi?.id?.let { it1 ->
                    Shinobi(
                        id = it1,
                        name = binding.editTextShinobiName.text.toString(),
                        village = binding.editTextShinobiVillageFrom.text.toString(),
                        description = binding.editTextShinobiDescription.text.toString()
                    )
                }
                GlobalScope.launch {
                    val database =
                        ShinobiDatabase.getInstace(this@DetailsActivity).shinobiDatabaseDao
                    shinobi?.let { database.update(it) }
                }
                finish()
            }
        }
        initDataIntent()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_details, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_menu_delete -> {
                shinobi?.let {
                    SampleData.deleteShinobi(it)
                }
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun addNewData() {
        var isEmptyField = false

        if (binding.editTextShinobiName.text.toString().isEmpty()) {
            isEmptyField = true
            binding.editTextShinobiName.error = getString(R.string.shinobi_name_error_message)
        }

        if (binding.editTextShinobiVillageFrom.text.toString().isEmpty()) {
            isEmptyField = true
            binding.editTextShinobiVillageFrom.error =
                getString(R.string.shinobi_village_error_message)
        }

        if (binding.editTextShinobiDescription.text.toString().isEmpty()) {
            isEmptyField = true
            binding.editTextShinobiDescription.error =
                getString(R.string.shinobi_description_error_message)
        }

        if (!isEmptyField) {
            val shinobi = Shinobi()
            shinobi.name = binding.editTextShinobiName.text.toString()
            shinobi.village = binding.editTextShinobiVillageFrom.text.toString()
            shinobi.description = binding.editTextShinobiDescription.text.toString()
            GlobalScope.launch {
                val database = ShinobiDatabase.getInstace(this@DetailsActivity).shinobiDatabaseDao
                database.insert(shinobi)
            }
        }
    }

    private fun getIntentType(): Boolean =
        DATA_SHINOBI_KEY == intent.getStringExtra(DATA_SHINOBI_TYPE)


    private fun getDataIntent(): Shinobi? = intent.getParcelableExtra(DATA_SHINOBI_KEY)

    private fun initDataIntent() {
        val intentType = getIntentType()
        if (intentType) {
            binding.buttonSave.text = getString(R.string.change)
            shinobi = getDataIntent()
            binding.editTextShinobiName.setText(shinobi?.name)
            binding.editTextShinobiVillageFrom.setText(shinobi?.village)
            binding.editTextShinobiDescription.setText(shinobi?.description)
        }
    }
}

