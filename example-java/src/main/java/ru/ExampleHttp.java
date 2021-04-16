package ru;

import io.art.model.annotation.*;
import io.art.model.configurator.*;
import io.netty.handler.codec.http.*;
import reactor.netty.http.*;
import ru.model.*;
import ru.service.*;

import java.io.*;

import static io.art.http.module.HttpModule.*;
import static io.art.launcher.ModuleLauncher.*;
import static io.art.model.configurator.ModuleModelConfigurator.*;
import static io.art.value.constants.ValueModuleConstants.DataFormat.*;
import static ru.ExampleHttpProvider.*;
import static ru.model.HttpResponse.*;

public class ExampleHttp {

    public static void main(String[] args) {
        launch(provide());
    }

    @Configurator
    public static ModuleModelConfigurator configure() {
        return module(ExampleHttp.class)
                .serve(server -> server
                        .http(http -> http
                                .host("0.0.0.0")
                                .protocol(HttpProtocol.HTTP11)
                                .logging(false)
                                .wiretap(false)
                                .accessLogging(false)
                                .defaultDataFormat(JSON)
                                .route("/", MyHttpService.class, route->route
                                        .get("method1", method -> method
                                                .path("{id}/1"))
                                        .post("method2", method -> method
                                                .path("2"))
                                        .websocket("websocket", method -> method
                                                .logging(true))
                                        .websocket("wsFlux")
                                        .file("file", "C:" + File.separator + "example.txt")
                                        .directory("directory", "C:" + File.separator)
                                )
                                .exceptions(e -> e
                                        .on(HttpExampleException.class, 404, () -> httpResponse("httpExampleException"))
                                        .on(IllegalStateException.class, exception -> {
                                            httpContext().status(405);
                                            return httpResponse(exception.getMessage());
                                        })
                                        .on(Throwable.class, HttpResponseStatus.CONFLICT)
                                )
                        )
                ); 
    }
}
