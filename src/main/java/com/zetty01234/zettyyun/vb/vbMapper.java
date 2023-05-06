package com.zetty01234.zettyyun.vb;

import com.zetty01234.zettyyun.file.fileDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;


@Mapper
public interface vbMapper {
    ArrayList<vbDTO> selectVbList() throws Exception;

    String selectThumb(@Param("vSeq")int v_seq) throws Exception;

    void insertVb(vbDTO dto) throws Exception;

    void insertFile(fileDTO dto) throws Exception;

    void updateVTpath(@Param("vSeq") int v_seq, @Param("vTpath") String v_tpath) throws Exception;

    vbDTO selectVb(@Param("vLink") String v_link) throws Exception;

    void deleteVb(String v_link) throws Exception;

    void updateVb(vbDTO dto) throws Exception;

    String selectFilePath(@Param("oTable") String o_table, @Param("oSeq") int o_seq) throws Exception;

    void deleteFile(@Param("oTable") String o_table, @Param("oSeq") int o_seq) throws Exception;

    void updateFile(fileDTO dto) throws Exception;
}
