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
    @Before
    public void setup() {
        server = new HelloHeroku().createServer();
        server.start();
    }

    @After
    public void teardown() {
        server.stop();
    }

    @Test
    public void testRoot() throws IOException {
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            String response = EntityUtils.toString(client.execute(new HttpGet("http://localhost:8080/")).getEntity(), "UTF-8");

            assertEquals("Hello Xplore", response);
        }


    }
}
