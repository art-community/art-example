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
                .produce { "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "  <head>\n" +
                        "    <meta charset=\"UTF-8\" />\n" +
                        "    <title>Add React in One Minute</title>\n" +
                        "  </head>\n" +
                        "  <body>\n" +
                        "\n" +
                        "    <h2>Add React in One Minute</h2>\n" +
                        "    <p>This page demonstrates using React with no build tooling.</p>\n" +
                        "    <p>React is loaded as a script tag.</p>\n" +
                        "\n" +
                        "    <!-- We will put our React component inside this div. -->\n" +
                        "    <div id=\"like_button_container\"></div>\n" +
                        "\n" +
                        "    <!-- Load React. -->\n" +
                        "    <!-- Note: when deploying, replace \"development.js\" with \"production.min.js\". -->\n" +
                        "    <script src=\"https://unpkg.com/react@16/umd/react.development.js\" crossorigin></script>\n" +
                        "    <script src=\"https://unpkg.com/react-dom@16/umd/react-dom.development.js\" crossorigin></script>\n" +
                        "\n" +
                        "    <!-- Load our React component. -->\n" +
                        "    <script src=\"like_button.js\"></script>\n" +
                        "\n" +
                        "  </body>\n" +
                        "</html>" }

        startHttpServer().await()
    }
}
