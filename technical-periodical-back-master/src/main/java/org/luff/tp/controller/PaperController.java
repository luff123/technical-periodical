package org.luff.tp.controller;

import org.luff.tp.model.PaperModel;
import org.luff.tp.model.PaperShortcut;
import org.luff.tp.service.PaperService;
import org.luff.tp.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PaperController {

    public static class PaperEncapsulation {
        public PaperModel paperModel;
        public boolean isStar;

        public PaperEncapsulation(PaperModel paperModel, boolean isStar) {
            this.paperModel = paperModel;
            this.isStar = isStar;
        }
    }

    @Autowired
    PaperService paperService;

    @Autowired
    StarService starService;

    @RequestMapping("/index")
    @ResponseBody
    public List<PaperShortcut> index(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return paperService.toShortcut(paperService.selectOrderByDate(10));
    }

    @RequestMapping("/paper")
    @ResponseBody
    public PaperEncapsulation paper(String id, String userId, HttpServletRequest request, HttpServletResponse response) {

        response.setHeader("Access-Control-Allow-Origin", "*");
        boolean isStar = starService.isStar(userId, Integer.valueOf(id));
        PaperModel paperModel = paperService.toModel(paperService.selectById(Integer.valueOf(id)));
        return new PaperEncapsulation(paperModel, isStar);
    }

    @RequestMapping("/papers")
    @ResponseBody
    public List<PaperShortcut> papers(String message, String id, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return switch (message) {
            case "periodical" -> paperService.toShortcut(paperService.selectByPeriodical(Integer.valueOf(id)));
            case "star" -> paperService.toShortcut(paperService.selectByStar(id));
            default -> new ArrayList<>();
        };
    }

    @RequestMapping("/search_paper")
    @ResponseBody
    public List<PaperShortcut> search(
            String keyword, String startDate, String endDate, String classify,
            HttpServletResponse response
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return paperService.toShortcut(paperService.selectByKeyDateClassify(keyword, startDate, endDate, classify));
    }
}
