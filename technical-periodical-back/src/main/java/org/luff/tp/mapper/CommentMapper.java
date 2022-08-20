package org.luff.tp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.luff.tp.pojo.Comment;

import java.util.List;

@Mapper
public interface CommentMapper {
    Comment selectById(@Param("id") Integer id);
    List<Comment> selectByPaperOrderByDate(@Param("paperId") Integer paperId);
    void insert(Comment comment);
}
