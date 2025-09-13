package com.example.nextapptest.core

sealed class Status<out T> {
    data class Success<out T>(val data: T) : Status<T>()
    data class Error(val exception: Throwable) : Status<Nothing>()
}