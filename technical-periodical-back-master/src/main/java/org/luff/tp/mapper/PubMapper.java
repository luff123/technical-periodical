package org.luff.tp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.luff.tp.pojo.Pub;

import java.util.List;

@Mapper
public interface PubMapper {
    List<Integer> selectPeriodicalByUserId(@Param("userId") String userId);
    void insert(Pub pub);
    void delete(Pub pub);
    Pub select(Pub pub);
}
