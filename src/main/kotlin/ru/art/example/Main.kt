package ru.art.example

import ru.art.config.extensions.activator.AgileConfigurationsActivator.*
import ru.art.entity.PrimitiveMapping.*
import ru.art.http.constants.MimeToContentTypeMapper.*
import ru.art.http.server.HttpServer.*
import ru.art.http.server.function.HttpServiceFunction.*
import ru.art.http.server.module.HttpServerModule.*

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        useAgileConfigurations("example")

        httpGet("${httpServerModule().path}/test")
                .responseMapper(stringMapper.fromModel)
                .producesMimeType(textHtmlUtf8())
                .produce {"<h1>Hello, world!</h1>"}

        startHttpServer().await()
    }
}
