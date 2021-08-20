package ru

import io.art.launcher.kotlin.*
import io.art.logging.kotlin.info
import io.art.transport.kotlin.asPrettyString
import ru.meta.MetaExampleKotlin
import ru.model.Model

// Здесь будет сладко

// Здесь просто - есть метатип, делаем из него ямл
fun не_магия() {
    info(Model(sequenceOf("не сахар")).asPrettyString())
}

// Здесь магия (мы выводим в yaml на базе не объявленных мета-типов) - с помощью рефлекшона и котлин выведения
fun магия() {
    info(mapOf("типа ключ" TO sequenceOf(mapOf("сладкий" to "сахар"))).asPrettyString())
}

fun main() = activator {
    meta(::MetaExampleKotlin)
    configurator()
    logging()
    yaml()
    transport()
    onLaunch {
        не_магия()
        магия()
    }
    launch()
}
