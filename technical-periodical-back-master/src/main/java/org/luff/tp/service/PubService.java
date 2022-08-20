package org.luff.tp.service;

import org.luff.tp.mapper.PubMapper;
import org.luff.tp.pojo.Pub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PubService {

    @Autowired
    PubMapper pubMapper;

    public String pub(String userId, Integer periodicalId) {
        pubMapper.insert(new Pub(userId, periodicalId));
        return "success";
    }

    public String disPub(String userId, Integer periodicalId) {
        pubMapper.delete(new Pub(userId, periodicalId));
        return "success";
    }

    public boolean isPub(String userId, Integer periodicalId) {
        if (userId.equals("0")) return false;
        return pubMapper.select(new Pub(userId, periodicalId)) != null;
    }
}
