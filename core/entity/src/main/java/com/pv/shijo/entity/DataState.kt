package com.pv.shijo.entity

sealed class DataState<out R> {
    data class Data<out R>(val data: R) : DataState<R>()
    class Loading<out R>() : DataState<R>()
}
