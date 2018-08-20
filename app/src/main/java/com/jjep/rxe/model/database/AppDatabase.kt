package com.jjep.rxe.model.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.jjep.rxe.model.Post
import com.jjep.rxe.model.PostDao

@Database(entities = [(Post::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}