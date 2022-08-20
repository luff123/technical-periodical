package org.luff.tp.service;

import org.luff.tp.mapper.StarMapper;
import org.luff.tp.pojo.Star;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StarService {

    @Autowired
    StarMapper starMapper;

    public String star(String userId, Integer paperId) {
        starMapper.insert(new Star(userId, paperId));
        return "success";
    }

    public String disStar(String userId, Integer paperId) {
        starMapper.delete(new Star(userId, paperId));
        return "success";
    }

    public boolean isStar(String userId, Integer paperId) {
        if (userId.equals("0")) return false;
        return starMapper.select(new Star(userId, paperId)) != null;
    }
}
