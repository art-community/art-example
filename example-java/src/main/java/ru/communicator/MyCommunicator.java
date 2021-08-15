package ru.communicator;

import io.art.core.communication.*;
import ru.model.*;

public interface MyCommunicator extends Communicator {
    Model myMethod(Model request);
}
