package com.qfedu.fmmall.webSocket;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/websocket/{oid}")
public class WebSocketServer {
    private static final ConcurrentHashMap<String, Session> SESSION_MAP = new ConcurrentHashMap<>();

    /**
     * Send a request to establish a websocket connection.
     */
    @OnOpen
    public void open(@PathParam("oid") String orderId, Session session) {
        SESSION_MAP.put(orderId, session);
    }

    /**
     * Close the page or close the websocket connection.
     */
    @OnClose
    public void close(@PathParam("oid") String orderId) {
        SESSION_MAP.remove(orderId);
    }

    public static void sendMsg(String orderId, String msg) {
        try {
            Session session = SESSION_MAP.get(orderId);
            session.getBasicRemote().sendText(msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
