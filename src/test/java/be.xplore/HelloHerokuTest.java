package be.xplore;


import io.undertow.Undertow;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class HelloHerokuTest {

    private Undertow server;
    private HelloHeroku helloHeroku;

    @Before
    public void setup() {
        helloHeroku = new HelloHeroku();
        server = helloHeroku.createServer();
        server.start();
    }

    @After
    public void teardown() {
        server.stop();
    }

    @Test
    public void testRoot() throws IOException {
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            String response = EntityUtils.toString(client.execute(new HttpGet("http://" + helloHeroku.listenerHost() + ":" + helloHeroku.listenerPort())).getEntity(), "UTF-8");

            assertEquals("Hello World", response);
        }


    }
}
