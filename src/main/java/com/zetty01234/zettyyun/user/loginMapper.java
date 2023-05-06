package com.zetty01234.zettyyun.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface loginMapper {
    userDTO selectUserByLogin(@Param("id") String id, @Param("pw") String pw);
}
