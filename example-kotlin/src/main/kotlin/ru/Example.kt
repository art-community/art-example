package ru

import io.art.configurator.kotlin.configurator
import io.art.launcher.kotlin.activator
import io.art.logging.kotlin.info
import io.art.logging.kotlin.logging
import io.art.message.pack.kotlin.messagePack
import io.art.meta.kotlin.KotlinMetaActivator.meta
import io.art.rsocket.kotlin.rsocket
import io.art.rsocket.kotlin.rsocketConnector
import io.art.transport.kotlin.transport
import io.art.yaml.kotlin.yaml
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
        server { server -> server.tcp().service(MyService::class.java) }
        communicator { communicator -> communicator.tcp(MyConnector::class.java) }
    }
    onLaunch {
        rsocketConnector<MyConnector> {
            info(my().myMethod(Model(sequenceOf("request"))).value.first().toString())
        }
    }
    launch().block()
}
