package com.jjep.rxe.ui.post

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.jjep.rxe.R
import com.jjep.rxe.base.BaseViewModel
import com.jjep.rxe.model.Post
import com.jjep.rxe.model.PostDao
import com.jjep.rxe.network.PostApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostListViewModel(private val postDao: PostDao) : BaseViewModel() {
    private lateinit var subscription: Disposable

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }
    val postListAdapter: PostListAdapter = PostListAdapter()

    @Inject
    lateinit var postApi: PostApi

    init {
        loadPosts()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadPosts() {
        subscription = Observable.fromCallable { postDao.all }
            .concatMap { list ->
                if (list.isEmpty())
                    postApi.getAll().concatMap { list ->
                        postDao.insert(*list.toTypedArray())
                        Observable.just(list)
                    }
                else
                    Observable.just(list)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { result -> onRetrievePostListSuccess(result) },
                { onRetrievePostListError() }
            )
    }

    private fun onRetrievePostListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePostListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(posts: List<Post>) {
        postListAdapter.updatePostList(posts)
    }

    private fun onRetrievePostListError() {
        errorMessage.value = R.string.post_error
    }
}