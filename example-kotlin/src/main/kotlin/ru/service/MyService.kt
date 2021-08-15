package ru.service

import io.art.logging.kotlin.info
import ru.communicator.MyCommunicator
import ru.model.Model

object MyService : MyCommunicator {
    override fun myMethod(model: Model): Model {
        info("myMethod1")
        return model.copy(value = "${model.value}: response")
    }
}
