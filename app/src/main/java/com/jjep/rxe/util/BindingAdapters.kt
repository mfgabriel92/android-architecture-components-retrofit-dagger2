package com.jjep.rxe.util

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.databinding.BindingAdapter
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.jjep.rxe.util.extension.getParentActivity
import com.squareup.picasso.Picasso

@BindingAdapter("mutableImage")
fun setMutableIage(view: ImageView, path: String?) {
    val parent: AppCompatActivity? = view.getParentActivity()

    if (parent != null) {
        Picasso.get()
            .load(path)
            .noPlaceholder()
            .centerCrop()
            .fit()
            .into(view)
    }
}

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parent: AppCompatActivity? = view.getParentActivity()

    if (parent != null && visibility != null) {
        visibility.observe(parent, Observer { value -> view.visibility = value ?: View.VISIBLE })
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()

    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value ?: "" })
    }
}

@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}