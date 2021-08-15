package ru.communicator

import io.art.core.communication.Connector

interface MyConnector : Connector {
    fun my(): MyCommunicator
}
