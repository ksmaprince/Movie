package com.ksma.movie.feature.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.ksma.domain.model.MovieType
import com.ksma.movie.R
import com.ksma.movie.databinding.ActivityMainBinding
import com.ksma.movie.feature.ui.movielist.MovieListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var popularContainer: FrameLayout
    private lateinit var upComingContainer: FrameLayout

    companion object{
        fun newInstance(context: Context): Intent{
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        popularContainer = binding.popularContainer
        upComingContainer = binding.upComingContainer
    }

    override fun onStart() {
        super.onStart()
        setup()
    }

    private fun setup() {

        val upComingFragment = MovieListFragment.newInstance(MovieType.UPCOMING_MOVIE)
        val transactionFour = supportFragmentManager.beginTransaction()
        transactionFour.replace(upComingContainer.id, upComingFragment)
        transactionFour.commit()


        val popularMovieFragment = MovieListFragment.newInstance(MovieType.POPULAR_MOVIE)
        val transactionThree = supportFragmentManager.beginTransaction()
        transactionThree.replace(popularContainer.id, popularMovieFragment)
        transactionThree.commit()

    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(
            R.anim.anim_fade_in, R.anim.anim_fade_out
        )
    }


}