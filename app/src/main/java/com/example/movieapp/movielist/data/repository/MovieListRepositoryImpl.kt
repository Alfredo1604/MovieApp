package com.example.movieapp.movielist.data.repository

import coil.network.HttpException
import com.example.movieapp.movielist.data.local.movie.MovieDataBase
import com.example.movieapp.movielist.data.mapper.toMovie
import com.example.movieapp.movielist.data.mapper.toMovieEntity
import com.example.movieapp.movielist.data.remote.MovieApi
import com.example.movieapp.movielist.domain.model.Movie
import com.example.movieapp.movielist.domain.repository.MovieListRepository
import com.example.movieapp.movielist.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class MovieListRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val movieDataBase: MovieDataBase
) : MovieListRepository {
    override suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        category: String,
        pages: Int
    ): Flow<Resource<List<Movie>>> {
        return flow {
            emit(Resource.Loading(true))

            val localMovieList = movieDataBase.movieDao.getMovieListByCategory(category)

            val shouldLoadLocalMovie = localMovieList.isNotEmpty() && !forceFetchFromRemote

            if (shouldLoadLocalMovie){
                emit(Resource.Success(
                    data = localMovieList.map { movieEntity ->
                        movieEntity.toMovie(category)
                    }
                ))

                emit(Resource.Loading(false))
                return@flow
            }

            val movieListFromApi = try {
                movieApi.getMoviesList(category, pages)
            } catch (e:IOException){
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies"))
                return@flow
            }
            catch (e:HttpException){
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies"))
                return@flow
            }
            catch (e:Exception){
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading movies"))
                return@flow
            }

            val movieEntities = movieListFromApi.results.let {
                it.map { movieDto ->
                    movieDto.toMovieEntity(category)
                }
            }

            movieDataBase.movieDao.upsertMovieList(movieEntities)

            emit(Resource.Success(
                movieEntities.map { it.toMovie(category) }
            ))

            emit(Resource.Loading(false))

        }
    }

    override suspend fun getMovie(id: Int): Flow<Resource<Movie>> {
        return flow {
            emit(Resource.Loading(true))

            val movieEntity = movieDataBase.movieDao.getMovieById(id)

            if (movieEntity != null){
                emit(Resource.Success(movieEntity.toMovie(movieEntity.category)))
                emit(Resource.Loading(false))
                return@flow
            }

            emit(Resource.Error("Error no such a movie"))
            emit(Resource.Loading(false))

        }
    }
}