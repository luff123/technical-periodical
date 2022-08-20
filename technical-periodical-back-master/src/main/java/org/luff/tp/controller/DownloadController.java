package org.luff.tp.controller;

import org.luff.tp.service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
public class DownloadController {

    @Autowired
    DownloadService downloadService;

    @RequestMapping("/download")
    @ResponseBody
    public String download(String paperId, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return downloadService.download(Integer.valueOf(paperId));
    }
}
