package com.ksma.movie.feature.ui.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ksma.domain.exception.NetworkException
import com.ksma.domain.model.MovieInfo
import com.ksma.domain.model.MovieType
import com.ksma.domain.viewstate.MovieListViewState
import com.ksma.movie.R
import com.ksma.movie.databinding.FragmentMovieListBinding
import com.ksma.movie.feature.base.BaseFragment
import com.ksma.movie.feature.ui.moviedetail.MovieDetailActivity
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import org.koin.android.ext.android.inject

class MovieListFragment : BaseFragment<MovieListViewState, MovieListView, MovieListPresenter>(),
    MovieListView {

    private val mPresenter: MovieListPresenter by inject()
    private val popularMovieRelay: PublishSubject<Int> = PublishSubject.create()
    private val upComingMovieRelay: PublishSubject<Int> = PublishSubject.create()
    private lateinit var mMovieType: MovieType
    private var mMovieId: Int = 0
    private lateinit var mMovieAdapter: MovieListRecyclerAdapter

    private var _binding: FragmentMovieListBinding? = null

    private val binding get() = _binding!!

    private lateinit var tvTitle: TextView
    private lateinit var tvSeeAll: TextView
    private lateinit var rvMovie: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var ivRefresh: ImageView

    companion object {
        fun newInstance(type: MovieType): MovieListFragment {
            val fragment = MovieListFragment()
            fragment.mMovieType = type
            return fragment
        }

        fun newInstance(type: MovieType, movieId: Int): MovieListFragment {
            val fragment = MovieListFragment()
            fragment.mMovieType = type
            fragment.mMovieId = movieId
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        tvTitle = binding.tvTitle
        tvSeeAll = binding.tvSeeAll
        rvMovie = binding.rvMovie
        progressBar = binding.progressBar.progress
        ivRefresh = binding.ivRefresh
        return root
    }

    override fun onStart() {
        super.onStart()
        setup()
        fetchMovieList()
    }

    private fun setup() {
        mMovieAdapter = MovieListRecyclerAdapter()
        rvMovie.setHasFixedSize(true)
        rvMovie.isNestedScrollingEnabled = false
        rvMovie.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvMovie.adapter = mMovieAdapter

        ivRefresh.setOnClickListener {
            fetchMovieList()
        }

        mMovieAdapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(movieInfo: MovieInfo) {
                startActivity(MovieDetailActivity.newInstance(requireContext(), movieInfo))
            }

            override fun onFavouriteClick(movieInfo: MovieInfo) {
                showToast("FavouritClick")
            }

        })
    }

    override fun createPresenter(): MovieListPresenter {
        return mPresenter
    }

    override fun render(viewState: MovieListViewState) {
        when (viewState) {
            is MovieListViewState.Progress -> renderProgress()
            is MovieListViewState.MovieList -> renderMovieList(viewState)
            is MovieListViewState.Error -> renderError(viewState)
        }
    }

    private fun renderError(viewState: MovieListViewState.Error) {
        dismissProgress()
        ivRefresh.visibility = View.VISIBLE
        when (viewState.t) {
            is NetworkException -> showToast("Network Error!")
            is NetworkException.ApiException -> showToast((viewState.t as NetworkException.ApiException).message)
            else -> viewState.t.printStackTrace()
        }
    }

    private fun renderMovieList(viewState: MovieListViewState.MovieList) {
        dismissProgress()
        mMovieAdapter.setMovies(viewState.movies)
    }

    private fun renderProgress() {
        ivRefresh.visibility = View.GONE
        progressBar.visibility = View.GONE
    }

    private fun dismissProgress() {
        progressBar.visibility = View.GONE
    }

    override fun popularMovieListIntent(): Observable<Int> {
        return popularMovieRelay
    }


    override fun upComingMovieListIntent(): Observable<Int> {
        return upComingMovieRelay
    }

    private fun fetchMovieList() {
        when (mMovieType) {

            MovieType.POPULAR_MOVIE -> {
                tvTitle.text = getString(R.string.popular)
                popularMovieRelay.onNext(1)
            }

            MovieType.UPCOMING_MOVIE -> {
                tvTitle.text = getString(R.string.upcoming_movie)
                upComingMovieRelay.onNext(1)
            }
        }
    }
}