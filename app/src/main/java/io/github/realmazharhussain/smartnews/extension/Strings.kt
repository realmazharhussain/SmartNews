package io.github.realmazharhussain.smartnews.extension

inline fun <T : CharSequence> T?.ifNullOrBlank(defaultValue: () -> T): T {
    return if (isNullOrBlank()) defaultValue() else this
}
