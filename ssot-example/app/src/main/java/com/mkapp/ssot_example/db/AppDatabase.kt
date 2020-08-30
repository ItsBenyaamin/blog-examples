package com.mkapp.ssot_example.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mkapp.ssot_example.MyApplication
import com.mkapp.ssot_example.db.dao.PostDao
import com.mkapp.ssot_example.db.entity.PostEntity

@Database(entities = [PostEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postsDao(): PostDao

    companion object {
        val instance by lazy {
            Room.databaseBuilder(
                MyApplication.instance.applicationContext,
                AppDatabase::class.java,
                "ssot_example_db"
            ).build()
        }
    }
}