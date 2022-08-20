package org.luff.tp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.luff.tp.pojo.Star;

import java.util.List;

@Mapper
public interface StarMapper {
    List<Integer> selectPaperByUserId(@Param("userId") String userId);
    void insert(Star star);
    void delete(Star star);
    Star select(Star star);
}
