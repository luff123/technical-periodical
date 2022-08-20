package org.luff.tp.controller;

import org.luff.tp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    @RequestMapping("/comment")
    @ResponseBody
    public String comment(String userId, String paperId, String date, String content, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return commentService.comment(userId, Integer.valueOf(paperId), date, content);
    }
}
