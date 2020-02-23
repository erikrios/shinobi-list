package com.erikriosetiawan.shinobilist.data

import com.erikriosetiawan.shinobilist.models.Shinobi

object SampleData {

    val shinobis: MutableList<Shinobi> = mutableListOf()

    init {
        for (i in 0..20) {
            val shinobi = Shinobi(
                i, "Shinobi $i", "Village $i", "Description $i"
            )
            shinobis.add(shinobi)
        }
    }
}