package com.zetty01234.zettyyun.ust;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ustServiceImpl implements ustService{
    @Autowired
    private ustMapper mapper;

    @Override
    public ArrayList<ustDTO> selectUstList() throws Exception {
        return mapper.selectUstList();
    }

    @Override
    public void insertUst(ustDTO dto) throws Exception {
        mapper.insertUst(dto);
    }

    @Override
    public void deleteUst(int u_seq) throws Exception {
        mapper.deleteUst(u_seq);
    }
}
