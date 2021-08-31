package ru.communicator;

import io.art.communicator.*;
import ru.model.*;

public interface MyCommunicator extends Communicator {
    Model myMethod(Model request);
}
