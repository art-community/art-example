package ru.communicator

import io.art.core.communication.Communicator
import ru.model.Model

interface MyCommunicator : Communicator {
    fun myMethod(model: Model): Model
}
