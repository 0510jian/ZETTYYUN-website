package com.zetty01234.zettyyun.ust;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface ustMapper {
    ArrayList<ustDTO> selectUstList() throws Exception;

    void insertUst(ustDTO dto) throws Exception;

    void deleteUst(@Param("uSeq") int u_seq) throws Exception;
}
