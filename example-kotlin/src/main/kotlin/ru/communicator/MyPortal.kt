package ru.communicator

import io.art.communicator.Portal

interface MyPortal : Portal {
    fun my(): MyCommunicator
}
