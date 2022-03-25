package com.xd.util;

/**
 *      store login user session and credential
 */
public class Principal {
    private String sessionId;
    private Object Credential;

    public Principal(){}

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }


    public Object getCredential() {
        return Credential;
    }

    public void setCredential(Object credential) {
        Credential = credential;
    }

    @Override
    public String toString() {
        return "Principal{" +
                "sessionId='" + sessionId + '\'' +
                ", Credential=" + Credential +
                '}';
    }
}
