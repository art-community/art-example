package ru.communicator

import io.art.communicator.Connector

interface MyConnector : Connector {
    fun my(): MyCommunicator
}
