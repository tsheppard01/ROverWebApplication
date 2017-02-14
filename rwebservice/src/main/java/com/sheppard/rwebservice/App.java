package com.sheppard.rwebservice;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;

import javax.websocket.server.ServerContainer;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //Setup jetty
        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8080);
        server.addConnector(connector);

        //Setup the context and handle any requests to "/"
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        try{

            //Start the websocket
            ServerContainer wsContainer = WebSocketServerContainerInitializer.configureContext(context);

            wsContainer.addEndpoint(MyWebSocket.class);

            server.start();
            server.dump(System.err);
            server.join();

        }
        catch (Throwable t){

            t.printStackTrace(System.err);

        }


    }
}
