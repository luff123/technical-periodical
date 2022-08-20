package org.luff.tp.service;

import org.luff.tp.mapper.CommentMapper;
import org.luff.tp.mapper.UserMapper;
import org.luff.tp.model.CommentModel;
import org.luff.tp.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    ReplyService replyService;

    public Comment selectById(Integer id) {
        return commentMapper.selectById(id);
    }

    public List<Comment> selectByPaper(Integer paperId) {
        return commentMapper.selectByPaperOrderByDate(paperId);
    }

    public CommentModel toModel(Comment comment) {
        return new CommentModel(
                comment.getId(),
                userMapper.selectNameById(comment.getUserId()),
                comment.getDate(),
                comment.getContent(),
                replyService.toModel(replyService.selectByComment(comment.getId()))
        );
    }

    public List<CommentModel> toModel(List<Comment> comments) {
        List<CommentModel> commentModels = new ArrayList<>();
        for (Comment comment : comments) {
            commentModels.add(toModel(comment));
        }
        return commentModels;
    }

    public String comment(String userId, Integer paperId, String date, String content) {
        commentMapper.insert(new Comment(-1, userId, paperId, date, content));
        return "success";
    }
}
