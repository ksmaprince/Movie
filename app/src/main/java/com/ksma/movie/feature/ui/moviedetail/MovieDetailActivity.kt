package com.ksma.movie.feature.ui.moviedetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.ksma.domain.model.MovieInfo
import com.ksma.domain.viewstate.MovieListViewState
import com.ksma.movie.R
import com.ksma.movie.databinding.ActivityMovieDetailBinding
import com.ksma.movie.feature.base.BaseActivity
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import org.koin.android.ext.android.inject

class MovieDetailActivity :
    BaseActivity<MovieListViewState, MovieDetailView, MovieDetailPresenter>(), MovieDetailView {

    private lateinit var binding: ActivityMovieDetailBinding

    private lateinit var ivPoster: ImageView
    private lateinit var ivFavourite: ImageView
    private lateinit var tvDate: TextView
    private lateinit var tvTitle: TextView
    private lateinit var tvOverview: TextView
    private lateinit var progressBar: ProgressBar

    private val movieDetailPresenter: MovieDetailPresenter by inject()
    private val favouriteMovieRelay: PublishSubject<Int> = PublishSubject.create()

    private lateinit var movieInfo: MovieInfo

    companion object {
        fun newInstance(context: Context, movieInfo: MovieInfo): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra("info", movieInfo)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        movieInfo = intent.getSerializableExtra("info") as MovieInfo

        ivPoster = binding.ivPoster
        ivFavourite = binding.ivFavourite
        tvDate = binding.tvDate
        tvTitle = binding.tvTitle
        tvOverview = binding.tvOverview
        progressBar = binding.progressBar.progress

        setup()
    }

    override fun onStart() {
        super.onStart()
        favouriteMovieRelay.onNext(0)
    }

    private var isSaveFavourite = false
    private fun setup() {
        Glide.with(this)
            .load(movieInfo.posterPath)
            .placeholder(R.drawable.movie_placeholder)
            .fitCenter()
            .into(ivPoster)

        tvDate.text = movieInfo.releaseDate
        tvTitle.text = movieInfo.title
        tvOverview.text = movieInfo.overview

        //To handle Favourite movie data by ROOM Db
        ivFavourite.setOnClickListener {
            isSaveFavourite = if (!isSaveFavourite) {
                Glide.with(this).load(R.drawable.ic_baseline_favorite_24)
                    .placeholder(R.drawable.ic_baseline_favorite_border_24).into(ivFavourite)
                true
            } else {
                Glide.with(this).load(R.drawable.ic_baseline_favorite_border_24)
                    .placeholder(R.drawable.ic_baseline_favorite_border_24).into(ivFavourite)
                false
            }
        }
    }

    override fun createPresenter(): MovieDetailPresenter {
        return movieDetailPresenter
    }

    override fun render(viewState: MovieListViewState) {
        when (viewState) {
            is MovieListViewState.Progress -> renderProgress()
            is MovieListViewState.Error -> renderError(viewState.t.localizedMessage)
            is MovieListViewState.MovieList -> renderMovieList(viewState.movies)
        }
    }

    private fun renderProgress() {
        progressBar.visibility = View.VISIBLE
    }

    private fun dismissProgress() {
        progressBar.visibility = View.GONE
    }

    private fun renderError(message: String) {
        dismissProgress()
        showToast(message)
    }

    private fun renderMovieList(movies: List<MovieInfo>) {
        dismissProgress()
        if (movies.isNotEmpty()) {
            isSaveFavourite = if (movies.contains(movieInfo)) {
                Glide.with(this).load(R.drawable.ic_baseline_favorite_24)
                    .placeholder(R.drawable.ic_baseline_favorite_border_24).into(ivFavourite)
                true
            } else {
                Glide.with(this).load(R.drawable.ic_baseline_favorite_border_24)
                    .placeholder(R.drawable.ic_baseline_favorite_border_24).into(ivFavourite)
                false
            }
        }
    }

    override fun FavouriteMovieIntent(): Observable<Int> {
        return favouriteMovieRelay
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(
            R.anim.anim_fade_in, R.anim.anim_fade_out
        )
    }
}