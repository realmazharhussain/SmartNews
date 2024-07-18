package io.github.realmazharhussain.smartnews.common

sealed interface Result<out T> {
    data class Success<out T>(val data: T): Result<T>
    data class Failure(val cause: Throwable): Result<Nothing>
}