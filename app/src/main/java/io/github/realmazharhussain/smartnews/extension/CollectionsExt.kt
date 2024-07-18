package io.github.realmazharhussain.smartnews.extension

fun <T> T.repeat(count: Int) = buildList(count) { for(i in 0..<count) { add(this@repeat) } }
