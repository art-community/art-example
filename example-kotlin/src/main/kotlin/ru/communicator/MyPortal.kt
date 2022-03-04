package ru.communicator

import io.art.communicator.model.ConnectorIdentifier

interface MyPortal : ConnectorIdentifier {
    fun my(): MyCommunicator
}
