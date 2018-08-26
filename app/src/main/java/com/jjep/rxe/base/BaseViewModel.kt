package com.jjep.rxe.base

import android.arch.lifecycle.ViewModel
import com.jjep.rxe.injection.component.DaggerViewModelInjector
import com.jjep.rxe.injection.component.ViewModelInjector
import com.jjep.rxe.injection.module.NetworkModule
import com.jjep.rxe.ui.post.PostListViewModel

abstract class BaseViewModel : ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

init {
    inject()
}

private fun inject() {
    when (this) {
        is PostListViewModel -> injector.inject(this)
    }
}
}