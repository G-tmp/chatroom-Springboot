package com.xd.config;

import com.xd.util.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Component
public class WSConnectInterceptor implements HandshakeInterceptor {

    @Autowired
    private HttpSession session;


    // check sessionId cookie in http request, if match server session, hand shake return success
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler webSocketHandler, Map<String, Object> attributes) throws Exception {

        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletServerRequest = (ServletServerHttpRequest) request;
            HttpServletRequest servletRequest = servletServerRequest.getServletRequest();

            Cookie cookie = WebUtils.getCookie(servletRequest, "JSESSIONID");
            Principal principal = (Principal) session.getAttribute("principal");

            if (cookie != null &&  principal!= null) {
                if (cookie.getValue().equals(principal.getSessionId())) {
                    attributes.put("principal", principal);
                    attributes.put("channelPath", servletRequest.getRequestURI());
                    return true;
                }
            }
        }

        return false;
    }


    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
        System.out.println("Shake hands success");
    }

}