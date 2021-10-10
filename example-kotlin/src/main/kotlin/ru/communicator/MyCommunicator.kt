package ru.communicator

import io.art.communicator.Communicator
import ru.model.Model

interface MyCommunicator : Communicator {
    fun myMethod(model: Model): Model
    fun getModel(): Model
}
