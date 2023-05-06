package com.zetty01234.zettyyun.vb;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public interface vbService {
    ArrayList<vbDTO> selectVbList() throws Exception;

    String selectThumb(int v_seq) throws Exception;

    void insertVb(vbDTO dto, MultipartHttpServletRequest multipartHttpServletRequest, HttpServletRequest request) throws Exception;

    vbDTO selectVb(String v_link) throws Exception;

    void deleteVb(String v_link) throws Exception;

    void updateVb(vbDTO dto) throws Exception;

    String selectFilePath(String o_table, int o_seq) throws Exception;

    void deleteFile(String o_table, int o_seq) throws Exception;

    void updateFile(int o_seq, MultipartHttpServletRequest multipartHttpServletRequest, HttpServletRequest request) throws Exception;
}
