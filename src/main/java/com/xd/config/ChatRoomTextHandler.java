package com.xd.config;

import com.xd.entity.Message;
import com.xd.entity.MessageConstant;
import com.xd.entity.User;
import com.xd.util.Principal;
import com.xd.util.RoomManager;
import com.xd.util.Subscriber;
import com.xd.util.WSChannelManager;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;
import java.util.*;

@Component
public class ChatRoomTextHandler extends TextWebSocketHandler {


    // send message event
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws InterruptedException, IOException {
        String channelPath = (String) session.getAttributes().get("channelPath");
        Principal principal = (Principal) session.getAttributes().get("principal");
        String sessionId = principal.getSessionId();

        String payload = textMessage.getPayload();
        Message message = new Message(payload);

        User user = (User) principal.getCredential();
        message.setType(MessageConstant.TYPE_OWNER)
                .setCommand(MessageConstant.COMMAND_SEND)
                .setSender(user.getNickname())
                .setTimestamp(System.currentTimeMillis())
                .setMsg(HtmlUtils.htmlEscape(message.getMsg()));

        // send message to owner
        sendOne(session, message.toJson());

        // send message to others
        message.setType(MessageConstant.TYPE_OTHERS);
        sendAllExcept(channelPath, message.toJson(), sessionId);
    }


    // onopen event
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String channelPath = (String) session.getAttributes().get("channelPath");
        Principal principal = (Principal) session.getAttributes().get("principal");
        User user = (User) principal.getCredential();
        String sessionId = principal.getSessionId();

        Message message = new Message();
        message.setType(MessageConstant.TYPE_OWNER)
                .setCommand(MessageConstant.COMMAND_ENTER)
                .setTimestamp(System.currentTimeMillis())
                .setChannel(channelPath)
                .setSender(user.getNickname());

        sendOne(session, message.toJson());

        message.setType(MessageConstant.TYPE_OTHERS);
        sendAll(channelPath, message.toJson());

        WSChannelManager.add(channelPath, sessionId, session);
    }

    // onclose event
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("close: " + session);

        Principal principal = (Principal) session.getAttributes().get("principal");
        String sessionId = principal.getSessionId();
        User user = (User) principal.getCredential();
        String channelPath = (String) session.getAttributes().get("channelPath");


        // someone leave
        Message message = new Message();
        message.setType(MessageConstant.TYPE_OTHERS)
                .setCommand(MessageConstant.COMMAND_LEAVE)
                .setTimestamp(System.currentTimeMillis())
                .setChannel(channelPath)
                .setSender(user.getNickname());

        sendAllExcept(channelPath, message.toJson(), sessionId);

        WSChannelManager.remove(channelPath, sessionId);
        if (WSChannelManager.subscriberSet(channelPath) == null)
            RoomManager.remove("");
    }



    private void sendOne(WebSocketSession session, String message) throws IOException {
        session.sendMessage(new TextMessage(message));
    }

    private void sendAll(String channelPath, String message) throws IOException {
        Set<Subscriber> set = WSChannelManager.subscriberSet(channelPath);
        if (set == null)
            return;

        for (Subscriber s:set){
            s.getWebSocketSession().sendMessage(new TextMessage(message));
        }
    }

    private void sendAllExcept(String channelPath, String message, String except) throws IOException {
        Set<Subscriber> set = WSChannelManager.subscriberSet(channelPath);
        if (set == null)
            return;

        for (Subscriber s:set){
            if (s.getSessionId().equals(except))
                continue;
            s.getWebSocketSession().sendMessage(new TextMessage(message));
        }
    }
}