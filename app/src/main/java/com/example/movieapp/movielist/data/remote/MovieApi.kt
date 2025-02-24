package com.example.movieapp.movielist.data.remote

import com.example.movieapp.movielist.data.remote.respond.MovieDto
import com.example.movieapp.movielist.data.remote.respond.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/{category}")
    suspend fun getMoviesList(
        @Path("category") category: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey:String = API_KEY
    ) : MovieListDto

    companion object{
        val BASE_URL = "https://api.themoviedb.org/3/"
        val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"
        val API_KEY = "0f171f1a7fc7d5d10e43ce201d395be1"
    }

}