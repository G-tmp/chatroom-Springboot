package com.xd.controller;

import com.xd.entity.User;
import com.xd.util.JsonRes;
import com.xd.util.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private HttpSession session;


    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public JsonRes login(@RequestBody Map<String, Object> payload) {
        String nickname = (String) payload.get("nickname");

        User user = new User();
        user.setNickname(nickname);

        String sessionId = session.getId();
        Principal principal = new Principal();
        principal.setSessionId(sessionId);
        principal.setCredential(user);

        session.setAttribute("principal", principal);

        JsonRes jsonRes = new JsonRes();
        jsonRes.setCode(JsonRes.SUCCESS_CODE)
                .setMsg(JsonRes.SUCCESS_MSG);

        return jsonRes;
    }

}
