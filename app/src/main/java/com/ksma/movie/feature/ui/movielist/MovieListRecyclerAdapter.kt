package com.ksma.movie.feature.ui.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ksma.domain.model.MovieInfo
import com.ksma.movie.R

class MovieListRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list = arrayListOf<MovieInfo>()

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    fun setMovies(movies: List<MovieInfo>) {
        list.clear()
        list.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_info, parent, false)
        return VHMovieInfo(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is VHMovieInfo -> holder.bind(list[position], listener!!)
        }
    }
}

interface OnItemClickListener {
    fun onItemClick(movieInfo: MovieInfo)
    fun onFavouriteClick(movieInfo: MovieInfo)
}