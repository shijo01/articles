package com.pv.shijo.entity

sealed class Response<out R> {
    data class Success<out T>(val data: T) : Response<T>()
    data class Failure<out T>(val error: String) : Response<T>()
}
