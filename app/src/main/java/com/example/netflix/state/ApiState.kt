package com.example.netflix.state

sealed class ApiState<out T> {
    data class Success<out T>(val data: T) : ApiState<T>()
    data class Error(val message: String) : ApiState<Nothing>()
    object Loading : ApiState<Nothing>()
}
