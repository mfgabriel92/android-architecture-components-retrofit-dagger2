package com.jjep.rxe.ui.post

import android.arch.lifecycle.MutableLiveData
import com.jjep.rxe.base.BaseViewModel
import com.jjep.rxe.model.Post

class PostViewModel : BaseViewModel() {
    private val title = MutableLiveData<String>()
    private val body = MutableLiveData<String>()

    fun bind(post: Post) {
        title.value = post.title
        body.value = post.body
    }

    fun getTitle(): MutableLiveData<String> {
        return title
    }

    fun getBody(): MutableLiveData<String> {
        return title
    }
}