package com.erikriosetiawan.shinobilist.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.erikriosetiawan.shinobilist.models.Shinobi

@Dao
interface ShinobiDatabaseDao {

    @Insert
    fun insert(shinobi: Shinobi)

    @Update
    fun update(shinobi: Shinobi)

    @Query("SELECT * FROM shinobi_table WHERE id = :id")
    fun get(id: Long): Shinobi?

    @Query("DELETE FROM shinobi_table")
    fun clear()

    @Query("SELECT * FROM shinobi_table ORDER BY id DESC LIMIT 1")
    fun getFirstShinobi(): Shinobi?

    @Query("SELECT * FROM shinobi_table ORDER BY id DESC")
    fun getAllShinobi(): List<Shinobi>?
}