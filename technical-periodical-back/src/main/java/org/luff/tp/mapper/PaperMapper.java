package org.luff.tp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.luff.tp.pojo.Paper;

import java.util.List;

@Mapper
public interface PaperMapper {
    List<Paper> selectOrderByDate(@Param("limit") Integer limit);
    Paper selectById(@Param("id") Integer id);
    List<Paper> selectByPeriodicalOrderByDate(@Param("periodicalId") Integer periodicalId);
    List<Paper> selectByPeriodicalOrderByDateTop3(@Param("periodicalId") Integer periodicalId);
    List<Paper> selectByKeyDateClassify(
            @Param("keyword") String keyword,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate,
            @Param("classify") String classify);

}
