package com.ksma.movie.feature.ui.movielist

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ksma.domain.model.MovieInfo
import com.ksma.movie.R
import com.ksma.movie.feature.base.BaseViewHolder

class VHMovieInfo(itemView: View) : BaseViewHolder(itemView) {


    fun bind(info: MovieInfo, listener: OnItemClickListener) {

        Glide.with(itemView.context)
            .load(info.posterPath)
            .placeholder(R.drawable.movie_placeholder)
            .into(itemView.findViewById(R.id.ivPoster))

        itemView.findViewById<ImageView>(R.id.ivPoster).setOnClickListener {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(info)
            }
        }
    }
}