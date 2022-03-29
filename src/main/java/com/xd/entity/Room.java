package com.xd.entity;

public class Room {

    private String id;
    private String name;
    private int onlineCount;
//    private int limitCount;

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

    public void plusOnlineCount(){
        this.onlineCount +=1;
    }

    public void minusOnlineCount(){
        this.onlineCount -=1;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", onlineCount=" + onlineCount +
                '}';
    }
}
