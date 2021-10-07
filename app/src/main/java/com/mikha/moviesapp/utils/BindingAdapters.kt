package com.mikha.moviesapp.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.mikha.moviesapp.R


@BindingAdapter("isNetworkError", "isLoading")
fun hideIfNetworkError(view: SwipeRefreshLayout, isNetworkError: Boolean, isLoading: Boolean){
    view.isRefreshing = isLoading

    if(isNetworkError) {
        view.isRefreshing = false
    }
}
@BindingAdapter("showOnNetworkError")
fun showIfNetworkError(view: View, showOnNetworkError: Boolean){
    view.visibility = if (showOnNetworkError) View.VISIBLE else View.GONE
}

@BindingAdapter("posterImageUrl")
fun posterImageUrl(imageView: ImageView, url: String?){
    val completeURL = "https://image.tmdb.org/t/p/w440_and_h660_face$url"
    Glide.with(imageView.context).load(completeURL).into(imageView)
}
