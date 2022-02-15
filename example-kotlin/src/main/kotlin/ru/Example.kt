package ru

import io.art.configurator.kotlin.configurator
import io.art.http.kotlin.http
import io.art.json.kotlin.json
import io.art.launcher.kotlin.activator
import io.art.logging.kotlin.logging
import io.art.message.pack.kotlin.messagePack
import io.art.meta.kotlin.meta
import io.art.rsocket.kotlin.rsocket
import io.art.transport.kotlin.transport
import io.art.yaml.kotlin.yaml
import ru.communicator.MyPortal
import ru.meta.MetaExampleKotlin
import ru.meta.MetaExampleKotlin.MetaRuPackage.MetaServicePackage.MetaMyServiceClass.Companion.myService
import ru.service.MyService

data class Request(val success: String)

fun main() = activator {
    meta(::MetaExampleKotlin)
    configurator()
    logging()
    json()
    yaml()
    messagePack()
    transport()
    rsocket {
        server { server -> server.tcp().service(MyService::class.java) }
        communicator { communicator -> communicator.tcp(MyPortal::class.java) }
    }
    http {
        server { server ->
            server
                    .routes(MyService::class.java)
                    .routes { myService() }
                    .route { myService().compensationMethod() }
                    .route { myService().getModelMethod() }
        }
        communicator { communicator -> communicator.portal(MyPortal::class.java) }
    }
    launch().block()
}
