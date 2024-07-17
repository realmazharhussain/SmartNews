package io.github.realmazharhussain.smartnews.common

/** The overall result of a task. Either [TaskState.Success] or [TaskState.Failure]. */
sealed interface Result<out T>: TaskState<T>

/** The state of a task. */
sealed interface TaskState<out T> {
    data class Success<out T>(val data: T): Result<T>
    data class Failure(val cause: Throwable): Result<Nothing>
    data class Loading(val progress: Int = PROGRESS_UNDEFINED): TaskState<Nothing> {
        companion object {
            const val PROGRESS_UNDEFINED = -1
        }
    }
}
