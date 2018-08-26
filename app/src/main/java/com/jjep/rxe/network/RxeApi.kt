package com.jjep.rxe.network

import com.jjep.rxe.model.post.Post
import io.reactivex.Observable
import retrofit2.http.GET

interface RxeApi {
    @GET("/posts")
    fun getAllPosts(): Observable<List<Post>>

    @GET("/posts/:id")
    fun getPost(): Observable<Post>
}