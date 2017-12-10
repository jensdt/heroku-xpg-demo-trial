package be.xplore;

import io.undertow.Undertow;
import io.undertow.util.Headers;

public class HelloHeroku {

    public static void main(String[] args) {
        Undertow server = Undertow.builder()
                .addHttpListener(8080, "0.0.0.0")
                .setHandler(exchange -> {
                    exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
                    exchange.getResponseSender().send("Hello World");
                }).build();

        server.start();

        System.out.println("Started server at http://127.1:8080/  Hit ^C to stop");
    }
}
