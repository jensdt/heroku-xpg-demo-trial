package be.xplore;

import io.undertow.Undertow;
import io.undertow.util.Headers;

public class HelloHeroku {

    public static void main(String[] args) {

        Undertow server = Undertow.builder()
                .addHttpListener(port(), "0.0.0.0")
                .setHandler(exchange -> {
                    System.out.println("Incoming request for " + exchange.getRequestPath());
                    exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
                    exchange.getResponseSender().send("Hello Xplore");
                }).build();

        server.start();

        System.out.println("Started server at http://127.1:8080/  Hit ^C to stop");
    }

    private static int port() {
        String envPort = System.getenv("PORT");

        if (envPort == null) {
            return 8080;
        } else {
            return Integer.parseInt(envPort);
        }
    }
}
