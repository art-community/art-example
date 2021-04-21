package ru.service;

import lombok.*;
import org.apache.logging.log4j.core.*;
import reactor.core.publisher.*;
import ru.model.*;
import static io.art.http.module.HttpModule.*;
import static io.art.logging.LoggingModule.*;
import static java.util.Objects.*;
import static ru.model.HttpResponse.*;


public class MyHttpService {
    private static final Logger logger = logger(MyHttpService.class);

    public HttpResponse method1(){
        logger.info("method1");
        Integer id = httpContext().getPathParams().get("id").getInt();
        return new HttpResponse("method1 ok. Id path parameter is " + id);
    }

    public HttpResponse method2(HttpResponse request){
        logger.info("method2");
        httpContext()
                .status(201)
                .header("header1", "value1");
        return httpResponse("method2 ok. Request was: " + (isNull(request) ? "null" : request.getMessage()));
    }

    public HttpResponse redirect(){
        logger.info("redirectMethod");
        httpContext().redirect("/file");
        return new HttpResponse("redirect");
    }

    @SneakyThrows
    public HttpResponse exampleException(){
        logger.info("exampleException");
        httpContext().status(201);
        throw new HttpExampleException("exampleException");
    }

    @SneakyThrows
    public HttpResponse illegalState(){
        logger.info("illegalState");
        throw new IllegalStateException("illegalState");
    }

    @SneakyThrows
    public HttpResponse throwable(){
        logger.info("throwable");
        throw new Throwable("throwable");
    }

    public HttpResponse websocket(HttpResponse req){
        logger.info("websocket");
        return isNull(req) ? httpResponse("null") : req;
    }

    public Flux<HttpResponse> wsFlux(Flux<HttpResponse> req){
        logger.info("wsFlux");
        return req;
    }
}
