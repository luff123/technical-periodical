package org.luff.tp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.luff.tp.pojo.User;

@Mapper
public interface UserMapper {
    String selectNameById(@Param("id") String id);
    User selectById(@Param("id") String id);
    void insert(User user);
    void setPassword(@Param("id") String id, @Param("password") String password);
    void setName(@Param("id") String id, @Param("name") String name);
}
