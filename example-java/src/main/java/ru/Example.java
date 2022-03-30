package ru;

import io.art.http.*;
import io.art.rsocket.*;
import ru.communicator.*;
import ru.meta.*;
import ru.model.*;
import ru.service.*;
import static io.art.configurator.module.ConfiguratorActivator.*;
import static io.art.http.module.HttpActivator.*;
import static io.art.json.module.JsonActivator.*;
import static io.art.launcher.Activator.*;
import static io.art.logging.Logging.*;
import static io.art.logging.module.LoggingActivator.*;
import static io.art.message.pack.module.MessagePackActivator.*;
import static io.art.meta.module.MetaActivator.*;
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
                .module(json())
                .module(yaml())
                .module(transport())
                .module(rsocket(rsocket -> rsocket
                        .server(server -> server.tcp().configure(configurator -> configurator.service(MyService.class)))
                        .communicator(communicator -> communicator.tcp(MyCommunicator.class))))
                .module(http(http -> http
                        .server(server -> server.routes(MyService.class))
                        .communicator(communicator -> communicator.connector(MyCommunicator.class))))
                .onLaunch(() -> {
                    logger().info(Rsocket.rsocket(MyCommunicator.class)
                            .myMethod(Model.builder().value("request").build())
                            .toString());
                    logger().info(Http.http(MyCommunicator.class)
                            .getModel()
                            .toString());
                })
                .launch()
                .block();
    }
}
