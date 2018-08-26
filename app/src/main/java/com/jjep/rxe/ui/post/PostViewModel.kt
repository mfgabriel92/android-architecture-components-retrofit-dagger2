package com.jjep.rxe.ui.post

import android.arch.lifecycle.MutableLiveData
import com.jjep.rxe.base.BaseViewModel
import com.jjep.rxe.model.post.Post

class PostViewModel : BaseViewModel() {
    private val image = MutableLiveData<String>()
    private val title = MutableLiveData<String>()
    private val body = MutableLiveData<String>()

    fun bind(post: Post) {
        image.value = post.image
        title.value = post.title
        body.value = post.body
    }

    fun getImage(): MutableLiveData<String> {
        return image
    }

    fun getTitle(): MutableLiveData<String> {
        return title
    }

    fun getBody(): MutableLiveData<String> {
        return body
    }
}