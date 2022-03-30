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
import ru.communicator.MyCommunicator
import ru.meta.MetaExampleKotlin
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
        server { server -> server.tcp().configure { configurator -> configurator.service(MyService::class.java) } }
        communicator { communicator -> communicator.tcp(MyCommunicator::class.java) }
    }
    http {
        server { server -> server.routes(MyService::class.java) }
        communicator { communicator -> communicator.connector(MyCommunicator::class.java) }
    }
    launch().block()
}
