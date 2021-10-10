package ru.service

import io.art.logging.kotlin.info
import ru.communicator.MyCommunicator
import ru.model.Model

object MyService : MyCommunicator {
    override fun myMethod(model: Model): Model {
        info("myMethod")
        return model.copy(value = sequenceOf("${model.value.first()}: response"))
    }

    override fun getModel(): Model = Model(sequenceOf("http: response")).apply { info("getModel") }
}
