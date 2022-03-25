package com.xd.util;

import org.springframework.web.socket.WebSocketSession;

import java.util.*;

/**
 *  store subscriber data
 */
public class Subscriber{
    private String sessionId;
    private WebSocketSession webSocketSession;

    public Subscriber(){}

    public Subscriber(String sessionId, WebSocketSession webSocketSession) {
        this.sessionId = sessionId;
        this.webSocketSession = webSocketSession;
    }

    public String getSessionId() {
        return sessionId;
    }

    public Subscriber setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public WebSocketSession getWebSocketSession() {
        return webSocketSession;
    }

    public Subscriber setWebSocketSession(WebSocketSession webSocketSession) {
        this.webSocketSession = webSocketSession;
        return this;
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "sessionId='" + sessionId + '\'' +
                ", webSocketSession=" + webSocketSession +
                '}';
    }


    /*
    *   Overwrite equals and hashCode for check whether two object equal
    *   Store unique item in set collection
     *  Compare session id only
    */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscriber that = (Subscriber) o;
        return sessionId.equals(that.sessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId);
    }

}
