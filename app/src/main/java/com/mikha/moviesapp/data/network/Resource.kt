package com.mikha.moviesapp.data.network

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}

fun Resource.Status.isLoading() : Boolean{
    if (this == null){
        return false
    }
    return this == Resource.Status.LOADING
}
fun Resource.Status.hasError() : Boolean{
    if (this == null){
        return false
    }
    return this == Resource.Status.ERROR
}