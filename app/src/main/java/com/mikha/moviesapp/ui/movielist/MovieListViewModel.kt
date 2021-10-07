package com.mikha.moviesapp.ui.movielist

import android.accounts.NetworkErrorException
import androidx.lifecycle.*
import com.mikha.moviesapp.data.network.Resource
import com.mikha.moviesapp.domain.model.Movie
import com.mikha.moviesapp.data.repository.MovieRepositoryImpl
import com.mikha.moviesapp.domain.usecase.GetMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieListUseCase: GetMovieListUseCase
) : ViewModel() {

    /**
     * Movies for home
     */
    val movies: MutableLiveData<List<Movie>> = MutableLiveData()

    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _eventIsLoading = MutableLiveData<Boolean>(false)
    val eventIsLoading: LiveData<Boolean>
        get() = _eventIsLoading


    init {
        getMoviesFromRepository()
    }

    fun getMoviesFromRepository(showLoading: Boolean = false){
        _eventIsLoading.value = true
        _eventNetworkError.value = false
        viewModelScope.launch(Dispatchers.IO) {
            movieListUseCase.getMovieList().collect {
                if (it.status == Resource.Status.SUCCESS){
                    movies.postValue(it.data ?: emptyList())
                    _eventIsLoading.postValue(false)
                    _eventNetworkError.postValue(false)
                }else{
                    _eventIsLoading.postValue(false)
                    _eventNetworkError.postValue(true)
                }
            }
//            Timber.i(response.toString())
//            if (response.isSuccess){
//                movies.value = response.getOrDefault(emptyList())
//                _eventIsLoading.value = false
//                _eventNetworkError.value = false
//            }else{
//                _eventIsLoading.value = false
//                _eventNetworkError.value = true
//            }
        }
    }

}