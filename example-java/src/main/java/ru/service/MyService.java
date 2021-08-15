package ru.service;

import ru.communicator.*;
import ru.model.*;
import static io.art.logging.Logging.*;

public class MyService implements MyCommunicator {
    @Override
    public Model myMethod(Model model) {
        logger(MyService.class).info("myMethod" + model);
        return model.toBuilder().value(model.getValue() + ": response").build();
    }
}
