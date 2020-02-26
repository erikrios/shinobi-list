package com.erikriosetiawan.shinobilist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.erikriosetiawan.shinobilist.models.Shinobi

@Database(entities = [Shinobi::class], version = 1, exportSchema = false)
abstract class ShinobiDatabase : RoomDatabase() {

    abstract val shinobiDatabaseDao: ShinobiDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: ShinobiDatabase? = null

        fun getInstace(context: Context): ShinobiDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ShinobiDatabase::class.java,
                        "shinobi_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}