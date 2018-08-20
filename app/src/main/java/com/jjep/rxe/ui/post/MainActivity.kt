package com.jjep.rxe.ui.post

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import com.jjep.rxe.R
import com.jjep.rxe.databinding.ActivityMainBinding
import com.jjep.rxe.injection.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: PostListViewModel
    private var error: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewModel()
        setupBindings()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(PostListViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer {
            message -> if (message != null) showError(message) else hideError()
        })
    }

    private fun setupBindings() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.rvPostList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.viewModel = viewModel
    }

    private fun showError(@StringRes message: Int) {
        error = Snackbar.make(binding.root, message, Snackbar.LENGTH_INDEFINITE)
        error?.setAction(R.string.retry, viewModel.errorClickListener)
        error?.show()
    }

    private fun hideError() {
        error?.dismiss()
    }
}
