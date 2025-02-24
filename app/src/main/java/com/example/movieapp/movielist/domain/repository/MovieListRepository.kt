package com.example.movieapp.movielist.domain.repository

import com.example.movieapp.movielist.domain.model.Movie
import com.example.movieapp.movielist.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieListRepository {

    suspend fun getMovieList(
        forceFetchFromRemote:Boolean,
        category:String,
        pages:Int
    ) : Flow<Resource<List<Movie>>>

    suspend fun getMovie(id:Int) : Flow<Resource<Movie>>
}