package com.xd.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Message implements MessageConstant{

    private int type;   // system, owner, others
    private int command; // enter_room, leave_room, send_msg
    private String sender;
    private String channel;
    private String msg;
    private String data;
    private long timestamp;


    public Message(){}

    public Message(String json){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Message message = objectMapper.readValue(json, Message.class);
            this.channel = message.getChannel();
            this.type = message.getType();
            this.sender = message.getSender();
            this.msg = message.getMsg();
            this.timestamp = message.getTimestamp();
            this.command = message.getCommand();
            this.data = message.getData();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


    public int getType() {
        return type;
    }

    public Message setType(int type) {
        this.type = type;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Message setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Message setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getSender() {
        return sender;
    }

    public Message setSender(String sender) {
        this.sender = sender;
        return this;
    }

    public String getChannel() {
        return channel;
    }

    public Message setChannel(String channel) {
        this.channel = channel;
        return this;
    }

    public int getCommand() {
        return command;
    }

    public Message setCommand(int command) {
        this.command = command;
        return this;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Message{" +
                "type=" + type +
                ", command=" + command +
                ", sender='" + sender + '\'' +
                ", channel='" + channel + '\'' +
                ", msg='" + msg + '\'' +
                ", data='" + data + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    public String toJson()  {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
