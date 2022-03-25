package com.xd.util;

import org.springframework.web.socket.WebSocketSession;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  Manage channel
 *
 *  Key: channelPath
 *  Value: subscriber object collection
 */
public class WSChannelManager {

    private static Map<String, Set<Subscriber>> CHANNELS = new ConcurrentHashMap<>();

    private WSChannelManager() {
    }

    public static boolean add(String channelPath, String sessionId, WebSocketSession session) {
        return add(channelPath, new Subscriber(sessionId, session));
    }

    public static boolean add(String channelPath, Subscriber subscriber) {
        Set<Subscriber> set = CHANNELS.get(channelPath);
        if (set == null) {
            set = new HashSet<>();
            CHANNELS.put(channelPath, set);
        }

        return set.add(subscriber);
    }

    public static boolean remove(String channelPath, String sessionId) {
        Subscriber subscriber = new Subscriber().setSessionId(sessionId);
        return remove(channelPath, subscriber);
    }

    //  remove subscriber from channel, if no subscriber exist remove entry
    public static boolean remove(String channelPath, Subscriber subscriber) {
        Set<Subscriber> set = CHANNELS.get(channelPath);
        if (set == null) {
            return false;
        }

        boolean b = set.remove(subscriber);

        if (set.size() == 0 )
            CHANNELS.remove(channelPath);

        return b;
    }

    public static Set<Subscriber> subscriberSet(String channelPath) {
        return CHANNELS.get(channelPath);
    }

}


