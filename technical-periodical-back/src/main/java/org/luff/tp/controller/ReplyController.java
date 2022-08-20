package org.luff.tp.controller;

import org.luff.tp.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
public class ReplyController {

    @Autowired
    ReplyService replyService;

    @RequestMapping("/reply")
    @ResponseBody
    public String reply(String userId, String commentId, String date, String content, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return replyService.reply(userId, Integer.valueOf(commentId), date, content);
    }
}
