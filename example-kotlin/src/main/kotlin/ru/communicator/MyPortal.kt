package ru.communicator

import io.art.communicator.ConnectorIdentifier

interface MyPortal : ConnectorIdentifier {
    fun my(): MyCommunicator
}
