package com.ksma.data.model.response

import com.ksma.data.model.response.MovieInfoResponse

data class PopularMovieResponse(val page: Int,
                                val total_results: Int,
                                val total_pages: Int,
                                val results: List<MovieInfoResponse>,
                                val status_code: Int,
                                val status_message: String)