package org.luff.tp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.luff.tp.pojo.Reply;

import java.util.List;

@Mapper
public interface ReplyMapper {
    Reply selectById(@Param("id") Integer id);
    List<Reply> selectByComment(@Param("commentId") Integer commentId);
    void insert(Reply reply);
}
