package com.zetty01234.zettyyun.vb;

import com.zetty01234.zettyyun.file.fileDTO;
import com.zetty01234.zettyyun.file.uploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@Service
public class vbServiceImpl implements vbService{
    @Autowired
    private vbMapper mapper;
    @Autowired
    private uploadFile uploadFile;

    @Override
    public ArrayList<vbDTO> selectVbList() throws Exception {
        return mapper.selectVbList();
    }

    @Override
    public String selectThumb(int v_seq) throws Exception {
        return mapper.selectThumb(v_seq);
    }

    @Override
    public void insertVb(vbDTO dto, MultipartHttpServletRequest multipartHttpServletRequest, HttpServletRequest request) throws Exception {
        mapper.insertVb(dto);

        fileDTO uploadDTO = uploadFile.parseFile("vb", dto.getVSeq(), multipartHttpServletRequest, request);
        if(uploadDTO != null) {
            mapper.insertFile(uploadDTO);
            mapper.updateVTpath(dto.getVSeq(), uploadDTO.getFilePath());
        }
    }





    @Override
    public vbDTO selectVb(String v_link) throws Exception {
        return mapper.selectVb(v_link);
    }

    @Override
    public void deleteVb(String v_link) throws Exception {
        mapper.deleteVb(v_link);
    }

    @Override
    public void updateVb(vbDTO dto) throws Exception {
        mapper.updateVb(dto);
    }

    @Override
    public String selectFilePath(String o_table, int o_seq) throws Exception {
        return mapper.selectFilePath(o_table, o_seq);
    }

    @Override
    public void deleteFile(String o_table, int o_seq) throws Exception {
        mapper.deleteFile(o_table, o_seq);
    }


    @Override
    public void updateFile(int o_seq, MultipartHttpServletRequest multipartHttpServletRequest, HttpServletRequest request) throws Exception {
        fileDTO uploadDTO = uploadFile.parseFile("vb", o_seq, multipartHttpServletRequest, request);

        mapper.updateFile(uploadDTO);
        mapper.updateVTpath(uploadDTO.getOSeq(), uploadDTO.getFilePath());
    }
}
