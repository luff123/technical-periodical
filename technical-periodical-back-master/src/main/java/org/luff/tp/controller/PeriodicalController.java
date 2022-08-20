package org.luff.tp.controller;

import org.luff.tp.model.PeriodicalModel;
import org.luff.tp.model.PeriodicalShortcut;
import org.luff.tp.service.PeriodicalService;
import org.luff.tp.service.PubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class PeriodicalController {

    public static class PeriodicalEncapsulation {
        public PeriodicalModel periodicalModel;
        public boolean isPub;

        public PeriodicalEncapsulation(PeriodicalModel periodicalModel, boolean isPub) {
            this.periodicalModel = periodicalModel;
            this.isPub = isPub;
        }
    }

    @Autowired
    PeriodicalService periodicalService;

    @Autowired
    PubService pubService;

    @RequestMapping("/periodicals")
    @ResponseBody
    public List<PeriodicalShortcut> periodicals(String id, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return periodicalService.selectByPub(id);
    }

    @RequestMapping("/search_periodical")
    @ResponseBody
    public List<PeriodicalShortcut> search(
            String keyword, String startDate, String endDate, String classify, HttpServletResponse response
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        System.out.println("参数是：");
        System.out.println(keyword + ":" + startDate + ":" + endDate + ":" +classify);
        System.out.println("返回的结果是：");
        List<PeriodicalShortcut> temp = periodicalService.selectByKeyDateClassify(keyword, startDate, endDate, classify);
        for (PeriodicalShortcut shortcut : temp) {
            System.out.println(shortcut.toString());
        }
        return periodicalService.selectByKeyDateClassify(keyword, startDate, endDate, classify);
    }

    @RequestMapping("/periodical")
    @ResponseBody
    public PeriodicalEncapsulation periodical(String id, String userId, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        boolean isPub = pubService.isPub(userId, Integer.valueOf(id));
        PeriodicalModel periodicalModel = periodicalService.toModel(periodicalService.selectById(Integer.valueOf(id)));
        return new PeriodicalEncapsulation(periodicalModel, isPub);
    }
}
