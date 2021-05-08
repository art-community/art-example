package ru;

import io.art.model.annotation.*;
import io.art.model.configurator.*;
import io.netty.handler.codec.http.*;
import java.io.*;
import reactor.netty.http.server.logging.*;
import ru.model.*;
import ru.service.*;
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
                                        .logging(false)
                                        .wiretap(false)
                                        .accessLogging(true)
                                        .accessLogFormat(request -> AccessLog.create("Access Log: method={}, uri={}", request.method(), request.uri()))
                                        .defaultDataFormat(JSON)

                                        .route("", MyHttpService.class, route->route
                                                .get("method1", method -> method
                                                        .path("{id}/1"))
                                                .post("method2", method -> method
                                                        .path("2"))
                                                .websocket("websocket", method -> method
                                                        .logging(true))
                                                .websocket("wsFlux")
                                                .file("file", "C:" + File.separator + "example.txt")
                                                .directory("directory", "C:", "index.html")
                                        )

                                        .authentication(authenticator -> authenticator
                                                .basic(MyHttpAuthenticator::check, auth -> auth
                                                        .on("/{id}/1", "/file")
                                                        .ignore("/234/1")
                                                )
                                        )

                                        .exception(HttpExampleException.class, 404, () -> httpResponse("httpExampleException"))
                                        .exception(IllegalStateException.class, exception -> {
                                            httpContext().status(405);
                                            return httpResponse(exception.getMessage());
                                        })
                                        .exception(Throwable.class, HttpResponseStatus.CONFLICT)
                        )
                ); 
    }
}
