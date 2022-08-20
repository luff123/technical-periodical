package org.luff.tp.service;

import org.luff.tp.mapper.PaperMapper;
import org.luff.tp.mapper.PeriodicalMapper;
import org.luff.tp.mapper.StarMapper;
import org.luff.tp.mapper.UserMapper;
import org.luff.tp.model.PaperModel;
import org.luff.tp.model.PaperShortcut;
import org.luff.tp.pojo.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaperService {

    @Autowired
    PaperMapper paperMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    PeriodicalMapper periodicalMapper;

    @Autowired
    StarMapper starMapper;

    @Autowired
    CommentService commentService;

    public PaperShortcut toShortcut(Paper paper) {
        return new PaperShortcut(
                paper.getId(),
                paper.getTitle(),
                userMapper.selectNameById(paper.getAuthorId()),
                paper.getDate(),
                paper.getSummary(),
                paper.getCoverPath()
        );
    }

    public List<PaperShortcut> toShortcut(List<Paper> papers) {
        List<PaperShortcut> paperShortcuts = new ArrayList<>();
        for (Paper paper : papers)
            paperShortcuts.add(toShortcut(paper));
        return paperShortcuts;
    }

    public PaperModel toModel(Paper paper) {
        PaperModel paperModel = null;
        String content = "";
        int begin = 0;
        int last = 0;
        try {
            content = Files.readString(Paths.get("src/main/resources/static/" + paper.getContent()));
            begin = content.indexOf("<body>") + 8;
            last = content.lastIndexOf("body") - 3;
        } catch (IOException e) {
            e.printStackTrace();
        }
        paperModel = new PaperModel(
                paper.getId(),
                paper.getTitle(),
                userMapper.selectNameById(paper.getAuthorId()),
                paper.getDate(),
                periodicalMapper.selectNameById(paper.getPeriodicalId()),
                paper.getSummary(),
                paper.getCoverPath(),
                content.substring(begin, last),
                paper.getClassify(),
                commentService.toModel(commentService.selectByPaper(paper.getId()))
        );
        return paperModel;
    }

    public List<Paper> selectOrderByDate(int limit) {
        return paperMapper.selectOrderByDate(limit);
    }

    public Paper selectById(Integer id) {
        return paperMapper.selectById(id);
    }

    public List<Paper> selectByPeriodical(Integer periodicalId) {
        return paperMapper.selectByPeriodicalOrderByDate(periodicalId);
    }

    public List<Paper> selectByStar(String userId) {
        List<Paper> papers = new ArrayList<>();
        List<Integer> paperIds = starMapper.selectPaperByUserId(userId);
        for (Integer paperId : paperIds) {
            papers.add(paperMapper.selectById(paperId));
        }
        return papers;
    }

    public List<Paper> selectByKeyDateClassify(String keyword, String startDate, String endDate, String classify) {
        keyword = keyword.trim();
        return paperMapper.selectByKeyDateClassify(keyword, startDate, endDate, classify);
    }
}
