package ru.communicator

import io.art.communicator.Portal

interface MyConnector : Portal {
    fun my(): MyCommunicator
}
