package com.jjep.rxe.model.post

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface PostDao {
    @get:Query("SELECT * FROM posts")
    val all: List<Post>

    @Insert
    fun insert(vararg posts: Post)
}