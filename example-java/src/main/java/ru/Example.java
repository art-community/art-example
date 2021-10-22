package ru;

import io.art.http.*;
import io.netty.channel.epoll.*;
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
import static io.art.rsocket.Rsocket.*;
import static io.art.rsocket.module.RsocketActivator.*;
import static io.art.transport.module.TransportActivator.*;
import static io.art.yaml.module.YamlActivator.*;

public class Example {
    public static void main(String[] arguments) {
        Native.newEpollCreate();
        Native.newEventFd();
    }
}
