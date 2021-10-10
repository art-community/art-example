package ru.communicator

import io.art.communicator.Communicator
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import ru.model.Model

interface MyCommunicator : Communicator {
    fun myMethod(model: Model): Model
    fun getModel(): Model
    fun compensation(input: Flux<String>): Mono<String>
}
