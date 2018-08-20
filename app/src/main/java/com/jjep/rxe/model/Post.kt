package com.jjep.rxe.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Class which provides a model for post
 * @constructor Sets all properties of the post
 * @property userId the unique identifier of the author of the post
 * @property id the unique identifier of the post
 * @property title the title of the post
 * @property body the content of the post
 */
@Entity(tableName = "posts")
data class Post(
    val userId: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val body: String
)