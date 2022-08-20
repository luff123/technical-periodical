package org.luff.tp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.luff.tp.pojo.Periodical;

import java.util.List;

@Mapper
public interface PeriodicalMapper {
    String selectNameById(@Param("id") Integer id);
    Periodical selectById(@Param("id") Integer id);
    List<Periodical> selectByKeyDateClassify(
            @Param("keyword") String keyword,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate,
            @Param("classify") String classify
    );
}
