package com.xd.entity;

public class Room {

    private String id;
    private String name;
    private int onlineCount;
    private int limitCount;

    public Room(){}

    public String getId() {
        return id;
    }

    public Room setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Room setName(String name) {
        this.name = name;
        return this;
    }

    public int getOnlineCount() {
        return onlineCount;
    }

    public Room setOnlineCount(int onlineCount) {
        this.onlineCount = onlineCount;
        return this;
    }

    public int getLimitCount() {
        return limitCount;
    }

    public Room setLimitCount(int limitCount) {
        this.limitCount = limitCount;
        return this;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", onlineCount=" + onlineCount +
                ", limitCount=" + limitCount +
                '}';
    }
}
