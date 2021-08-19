package ru.service;

import ru.communicator.*;
import ru.model.*;
import static io.art.logging.Logging.*;
import static io.art.transport.extensions.TransportExtensions.*;

public class MyService implements MyCommunicator {
    @Override
    public Model myMethod(Model model) {
        logger(MyService.class).info("myMethod:" + asPrettyString(model));
        return model.toBuilder().value(model.getValue() + ": response").build();
    }
}
