package ru

import io.art.launcher.kotlin.*
import io.art.logging.kotlin.info
import io.art.transport.kotlin.toPrettyString
import ru.meta.MetaExampleKotlin
import ru.model.Model

// Здесь будет сладко

// Здесь просто - есть метатип, делаем из него ямл
fun не_магия() {
    info(Model(sequenceOf("не сахар")).toPrettyString())
}

// Здесь магия (мы выводим в yaml на базе не объявленных мета-типов) - с помощью рефлекшона и котлин выведения
fun магия() {
    info(mapOf("типа ключ" to sequenceOf(mapOf("сладкий" to "сахар"))).toPrettyString())
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
