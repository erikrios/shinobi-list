package com.erikriosetiawan.shinobilist.data

import com.erikriosetiawan.shinobilist.models.Shinobi

object SampleData {

    var TOTAL = 21

    private val shinobis: MutableList<Shinobi> = mutableListOf()

    val getShinobis: MutableList<Shinobi>
        get() = shinobis

    init {
        for (i in 0..20) {
            val shinobi = Shinobi(
                i, "Shinobi $i", "Village $i", "Description $i"
            )
            shinobis.add(shinobi)
        }
    }

    fun addShinobi(shinobi: Shinobi) {
        shinobis.add(shinobi)
        TOTAL++
    }

    fun deleteShinobi(shinobi: Shinobi) {
        shinobis.remove(shinobi)
    }

    fun updateShinobi(id: Int?, newName: String, newVillage: String, newDescription: String) {
        shinobis.forEachIndexed { i, it ->
            if (it.id == id) {
                shinobis[i].apply {
                    name = newName
                    village = newVillage
                    description = newDescription
                }
            }
        }
    }
}