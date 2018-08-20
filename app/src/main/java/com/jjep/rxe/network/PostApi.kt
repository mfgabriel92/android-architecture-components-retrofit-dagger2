package com.jjep.rxe.network

import com.jjep.rxe.model.Post
import io.reactivex.Observable
import retrofit2.http.GET

interface PostApi {
    @GET("/posts")
    fun getAll(): Observable<List<Post>>
}