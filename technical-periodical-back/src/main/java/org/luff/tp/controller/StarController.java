package org.luff.tp.controller;

import org.luff.tp.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
public class StarController {

    @Autowired
    StarService starService;

    @RequestMapping("/star")
    @ResponseBody
    public String star(String userId, Integer paperId, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return starService.star(userId, paperId);
    }

    @RequestMapping("/dis_star")
    @ResponseBody
    public String disStar(String userId, String paperId, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return starService.disStar(userId, Integer.valueOf(paperId));
    }
}
