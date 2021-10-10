package ru.service

import io.art.core.kotlin.compensate
import io.art.logging.kotlin.info
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import ru.communicator.MyCommunicator
import ru.model.Model

object MyService : MyCommunicator {
    override fun myMethod(model: Model): Model {
        info("myMethod")

        return model.copy(value = sequenceOf("${model.value.first()}: response"))
    }

    override fun getModel(): Model = Model(sequenceOf("http: response")).apply { info("getModel") }

    override fun compensation(input: Flux<String>): Mono<String> = input
            .compensate({ value -> value == "error" }) { Flux.just("not error") }
            .last()
}
