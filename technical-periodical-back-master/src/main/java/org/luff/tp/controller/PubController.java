package org.luff.tp.controller;

import org.luff.tp.service.PubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
public class PubController {

    @Autowired
    PubService pubService;

    @RequestMapping("/pub")
    @ResponseBody
    public String pub(String userId, Integer periodicalId, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return pubService.pub(userId, periodicalId);
    }

    @RequestMapping("/dis_pub")
    @ResponseBody
    public String disPub(String userId, Integer periodicalId, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return pubService.disPub(userId, periodicalId);
    }
}
