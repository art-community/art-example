package ru

import io.art.communicator.configurator.CommunicatorActionConfigurator
import io.art.launcher.kotlin.*
import io.art.logging.kotlin.info
import io.art.rsocket.kotlin.rsocketConnector
import io.art.server.configurator.ServiceMethodConfigurator
import ru.communicator.MyCommunicator
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
        server { server ->
            server
                    .tcp()
                    .configureService(MyService::class.java, ServiceMethodConfigurator::logging)
        }
        communicator { communicator ->
            communicator
                    .tcp(MyConnector::class.java)
                    .configureCommunicator(MyCommunicator::class.java, CommunicatorActionConfigurator::logging)
        }
    }
    onLaunch {
        rsocketConnector<MyConnector> {
            info(my().myMethod(Model(sequenceOf("request"))).value.first().toString())
        }
    }
    launch()
}
