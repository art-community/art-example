package ru

import io.art.launcher.kotlin.*
import io.art.logging.kotlin.info
import io.art.rsocket.kotlin.rsocketConnector
import ru.communicator.MyConnector
import ru.meta.MetaExampleKotlin
import ru.model.Model
import ru.service.MyService

fun main() = activator {
    meta(::MetaExampleKotlin)
    configurator()
    logging()
    yaml()
    messagePack()
    transport()
    rsocket {
        server { server -> server.tcp().configureService(MyService::class.java) }
        communicator { communicator -> communicator.tcp(MyConnector::class.java) }
    }
    onLaunch {
        rsocketConnector<MyConnector> {
            info(my().myMethod(Model(sequenceOf("request"))).value.first().toString())
        }
    }
    launch()
}
