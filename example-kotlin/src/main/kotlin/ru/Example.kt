package ru

import io.art.configurator.kotlin.configurator
import io.art.http.kotlin.http
import io.art.http.kotlin.httpConnector
import io.art.json.kotlin.json
import io.art.launcher.kotlin.activator
import io.art.logging.kotlin.info
import io.art.logging.kotlin.logging
import io.art.message.pack.kotlin.messagePack
import io.art.meta.kotlin.meta
import io.art.rsocket.kotlin.rsocket
import io.art.rsocket.kotlin.rsocketConnector
import io.art.transport.kotlin.transport
import io.art.yaml.kotlin.yaml
import reactor.core.publisher.Flux
import ru.communicator.MyConnector
import ru.meta.MetaExampleKotlin
import ru.service.MyService

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
        communicator { communicator -> communicator.tcp(MyConnector::class.java) }
    }
    http {
        server { server -> server.route(MyService::class.java) }
        communicator { communicator -> communicator.connector(MyConnector::class.java) }
    }
    onLaunch {
        rsocketConnector<MyConnector> {
            my().compensation(Flux.just("error")).subscribe(::info)
        }
        httpConnector<MyConnector> {
            info(my().getModel().value.first().toString())
        }
    }
    launch()
}
