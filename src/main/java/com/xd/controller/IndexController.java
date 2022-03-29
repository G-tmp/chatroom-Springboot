package com.xd.controller;


import com.xd.entity.Room;
import com.xd.util.JsonRes;
import com.xd.util.Principal;
import com.xd.util.RoomManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private HttpSession session;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){
        Principal principal = (Principal) session.getAttribute("principal");
        if (principal == null)
            return "redirect:/login";

        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "loadIndex", method = RequestMethod.GET)
    public JsonRes load(){
        JsonRes jsonRes = new JsonRes();
        jsonRes.setCode(JsonRes.SUCCESS_CODE).setMsg(JsonRes.SUCCESS_MSG);

        Collection<Room> rooms = RoomManager.list().values();
        Map<String,Object> data = new HashMap<>();
        data.put("roomList", rooms);
        jsonRes.setData(data);

        return jsonRes;
    }

}
