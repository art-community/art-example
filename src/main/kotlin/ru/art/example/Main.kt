package ru.art.example

import ru.art.config.extensions.activator.AgileConfigurationsActivator.*
import ru.art.http.server.HttpServer.*

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        useAgileConfigurations()
        startHttpServer().await()
    }
}
