package io.github.realmazharhussain.smartnews.util

import io.github.realmazharhussain.smartnews.common.Result
import io.github.realmazharhussain.smartnews.common.TaskState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

@Suppress("unused")
fun <T> getResult(block: () -> T) = try {
    Result.Success(block())
} catch (t: Throwable) {
    Result.Failure(t)
}

suspend fun <T> getResultAsync(block: suspend () -> T) = try {
    Result.Success(block())
} catch (t: InternalError) {
    Result.Failure(t)
}

/** Execute [block] and return a [StateFlow] of [block]'s execution state */
@Suppress("unused")
fun <T> flowState(
    scope: CoroutineScope,
    started: SharingStarted = SharingStarted.Eagerly,
    block: suspend () -> T
) = flow {
    val result = getResultAsync(block)
    val state = TaskState.Completed(result)
    emit(state)
}.stateIn(scope, started, TaskState.Ongoing())
