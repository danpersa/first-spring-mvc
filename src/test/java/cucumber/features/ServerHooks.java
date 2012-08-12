package cucumber.features;

import cucumber.annotation.After;
import cucumber.annotation.Before;

import java.io.IOException;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

public class ServerHooks {

    public static final int PORT = 8887;
    public static final String CONTEXT = "/first-spring-mvc";
    private Server server;

    @Before
    public void startServer() throws IOException, Exception {
        server = new Server(PORT);
        WebAppContext webAppContext = new WebAppContext("src/main/webapp", CONTEXT);
        webAppContext.setClassLoader(ClassLoader.getSystemClassLoader());
        server.setHandler(webAppContext);
        server.start();
    }

    @After
    public void stopServer() throws IOException, Exception {
        server.stop();
    }
}
