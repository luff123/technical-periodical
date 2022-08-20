package org.luff.tp.service;

import org.luff.tp.mapper.ReplyMapper;
import org.luff.tp.mapper.UserMapper;
import org.luff.tp.model.ReplyModel;
import org.luff.tp.pojo.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReplyService {

    @Autowired
    ReplyMapper replyMapper;

    @Autowired
    UserMapper userMapper;

    public Reply selectById(Integer id) {
        return replyMapper.selectById(id);
    }

    public List<Reply> selectByComment(Integer commentId) {
        return replyMapper.selectByComment(commentId);
    }

    public ReplyModel toModel(Reply reply) {
        return new ReplyModel(
                userMapper.selectNameById(reply.getUserId()),
                reply.getDate(),
                reply.getContent()
        );
    }

    public List<ReplyModel> toModel(List<Reply> replies) {
        List<ReplyModel> replyModels = new ArrayList<>();
        for (Reply reply : replies) {
            replyModels.add(toModel(reply));
        }
        return replyModels;
    }

    public String reply(String userId, Integer commentId, String date, String content) {
        replyMapper.insert(new Reply(-1, userId, commentId, date, content));
        return "success";
    }
}
