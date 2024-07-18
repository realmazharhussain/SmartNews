package io.github.realmazharhussain.smartnews.common

sealed interface TaskState<out T> {
    data class Ongoing(val progress: Int = PROGRESS_UNDEFINED) : TaskState<Nothing> {
        companion object {
            const val PROGRESS_UNDEFINED = -1
        }
    }
    data class Completed<out T>(val result: Result<T>): TaskState<T>
}