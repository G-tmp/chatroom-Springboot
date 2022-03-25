package com.xd.util;

import com.xd.entity.Room;

import java.util.concurrent.ConcurrentHashMap;

/**
 *  Manage room
 *
 *  Key: room id
 *  Value: room object
*/

public class RoomManager {

    private static ConcurrentHashMap<String, Room> ROOMS = new ConcurrentHashMap<>();

    private RoomManager(){}

    public static void add(String id, Room room) {
        ROOMS.put(id, room);
    }


    public static void remove(String id) {
        Room room = ROOMS.remove(id);
    }


    public static Room get(String id) {
        return ROOMS.get(id);
    }


    public static ConcurrentHashMap<String, Room> list(){
        return ROOMS;
    }

}
