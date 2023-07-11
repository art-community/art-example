package ru.communicator

import io.art.http.communicator.HttpCommunicator
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import ru.model.Model

interface MyCommunicator : HttpCommunicator<MyCommunicator> {
    fun myMethod(model: Model): Model
    fun getModel(): Model
    fun compensation(input: Flux<String>): Mono<String>
}
