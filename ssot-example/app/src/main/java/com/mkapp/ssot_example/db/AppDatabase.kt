package com.mkapp.ssot_example.db

import androidx.room.Room
import androidx.room.RoomDatabase
import com.mkapp.ssot_example.MyApplication
import com.mkapp.ssot_example.db.dao.PostDao

abstract class AppDatabase : RoomDatabase() {
    abstract var dao: PostDao

    companion object {
        val instance by lazy {
            Room.databaseBuilder(
                MyApplication.instance,
                AppDatabase::class.java,
                "ssot_example_db"
            ).build()
        }
    }
}