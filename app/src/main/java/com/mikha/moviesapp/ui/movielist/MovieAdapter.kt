package com.mikha.moviesapp.ui.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mikha.moviesapp.R
import com.mikha.moviesapp.domain.model.Movie
import com.mikha.moviesapp.databinding.ItemMovieBinding

class MovieDiffCallback: DiffUtil.ItemCallback<Movie>(){
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

}


class MovieAdapter(private val callback: MovieClick): ListAdapter<Movie,MovieAdapter.MovieViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val dataBinding: ItemMovieBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            MovieViewHolder.LAYOUT,
            parent,
            false
        )

        return MovieViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        holder.viewDataBinding.also {
            it.movie = item
            it.movieCallback = callback
        }
    }


    class MovieViewHolder(val viewDataBinding: ItemMovieBinding) : RecyclerView.ViewHolder(viewDataBinding.root){
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.item_movie
        }
    }

    class MovieClick(val click: (Movie) -> Unit){
        fun onClick(movie: Movie) = click(movie)
    }


}



