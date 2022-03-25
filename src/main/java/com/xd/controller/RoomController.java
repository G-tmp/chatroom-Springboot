package com.xd.controller;


import com.xd.entity.Room;
import com.xd.util.JsonRes;
import com.xd.util.RoomManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class RoomController {

    @Autowired
    private HttpSession session;


    @RequestMapping(value = "/room/{roomId}",method = RequestMethod.GET)
    public String room(@PathVariable("roomId") String roomId, Model model){
        if (RoomManager.get(roomId) == null)
            return "404";

        return "room";
    }


    @ResponseBody
    @RequestMapping(value = "/room/load",method = RequestMethod.GET)
    public JsonRes loadRoom(String roomId){
        JsonRes jsonRes = new JsonRes();

        if (session.getAttribute("principal") == null){
            jsonRes.setCode(JsonRes.ERROR_CODE).setMsg("login first");
            return jsonRes;
        }

        Room room = RoomManager.get(roomId);

        jsonRes.setCode(JsonRes.SUCCESS_CODE).setCode(JsonRes.SUCCESS_CODE);
        Map<String, Object> data = new HashMap<>();
        data.put("channel", "/room_ws/" + roomId);
        data.put("room", room);

        jsonRes.setData(data);

        return  jsonRes;
    }

    @ResponseBody
    @RequestMapping("/room/create")
    public String create(){

        return "";
    }

//    @ResponseBody
//    @RequestMapping("/send")
//    public void send( String channel){
//        Set<Subscriber> set = WSChannelManager.subscriberSet(channel);
//        System.out.println(set);
//
//        Message message = new Message();
//        message.setSender("test").setMsg("TEST");
//        for (Subscriber e:set){
//            try {
//                e.getWebSocketSession().sendMessage(new TextMessage(message.toJson()));
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//    }
//
//    @ResponseBody
//    @RequestMapping("/list")
//    public void list(String channel){
//        System.out.println(channel);
//        Set<Subscriber> set = WSChannelManager.subscriberSet(channel);
//        System.out.println(set);
//    }
}
