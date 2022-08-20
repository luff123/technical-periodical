package org.luff.tp.service;

import org.luff.tp.mapper.PaperMapper;
import org.luff.tp.mapper.PeriodicalMapper;
import org.luff.tp.mapper.PubMapper;
import org.luff.tp.mapper.UserMapper;
import org.luff.tp.model.PeriodicalModel;
import org.luff.tp.model.PeriodicalShortcut;
import org.luff.tp.pojo.Periodical;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PeriodicalService {
    @Autowired
    PeriodicalMapper periodicalMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    PaperMapper paperMapper;

    @Autowired
    PaperService paperService;

    @Autowired
    PubMapper pubMapper;

    public PeriodicalShortcut toShortcut(Periodical periodical) {
        return new PeriodicalShortcut(
                periodical.getId(),
                periodical.getName(),
                userMapper.selectNameById(periodical.getAuthorId()),
                periodical.getDate(),
                periodical.getSummary(),
                periodical.getCoverPath(),
                periodical.getClassify()
        );
    }

    public List<PeriodicalShortcut> toShortcut(List<Periodical> periodicals) {
        List<PeriodicalShortcut> periodicalShortcuts = new ArrayList<>();
        for (Periodical periodical : periodicals) {
            periodicalShortcuts.add(toShortcut(periodical));
        }
        return periodicalShortcuts;
    }

    public PeriodicalModel toModel(Periodical periodical) {
        return new PeriodicalModel(
                periodical.getId(),
                periodical.getName(),
                userMapper.selectNameById(periodical.getAuthorId()),
                periodical.getDate(),
                periodical.getSummary(),
                periodical.getCoverPath(),
                periodical.getClassify(),
                paperService.toShortcut(paperMapper.selectByPeriodicalOrderByDateTop3(periodical.getId()))
        );
    }

    public List<PeriodicalShortcut> selectByPub(String userId) {
        List<PeriodicalShortcut> periodicalShortcuts = new ArrayList<>();
        List<Integer> periodicalIds = pubMapper.selectPeriodicalByUserId(userId);
        for (Integer periodicalId : periodicalIds) {
            periodicalShortcuts.add(toShortcut(periodicalMapper.selectById(periodicalId)));
        }
        return periodicalShortcuts;
    }

    public List<PeriodicalShortcut> selectByKeyDateClassify(String keyword, String startDate, String endDate, String classify) {
        keyword = keyword.trim();
        return toShortcut(periodicalMapper.selectByKeyDateClassify(keyword, startDate, endDate, classify));
    }

    public Periodical selectById(Integer id) {
        return periodicalMapper.selectById(id);
    }
}
