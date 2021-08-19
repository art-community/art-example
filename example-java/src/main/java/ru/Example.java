package ru;

import ru.communicator.*;
import ru.meta.*;
import ru.model.*;
import ru.service.*;
import static io.art.configurator.module.ConfiguratorActivator.*;
import static io.art.launcher.Activator.*;
import static io.art.logging.Logging.*;
import static io.art.logging.module.LoggingActivator.*;
import static io.art.message.pack.module.MessagePackActivator.*;
import static io.art.meta.module.MetaActivator.*;
import static io.art.rsocket.Rsocket.*;
import static io.art.rsocket.module.RsocketActivator.*;
import static io.art.transport.module.TransportActivator.*;
import static io.art.yaml.module.YamlActivator.*;

public class Example {
    public static void main(String[] arguments) {
        activator(arguments)
                .main(Example.class.getSimpleName())
                .module(meta(MetaExampleJava::new))
                .module(configurator())
                .module(logging())
                .module(messagePack())
                .module(yaml())
                .module(transport())
                .module(rsocket(rsocket -> rsocket
                        .server(server -> server.tcp().configureService(MyService.class))
                        .communicator(communicator -> communicator.tcp(MyConnector.class))))
                .onLaunch(() -> logger().info(rsocketConnector(MyConnector.class)
                        .my()
                        .myMethod(Model.builder().value("request").build())
                        .toString()))
                .launch();
    }
}
