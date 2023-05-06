package com.zetty01234.zettyyun.ust;

import java.util.ArrayList;

public interface ustService {
    ArrayList<ustDTO> selectUstList() throws Exception;

    void insertUst(ustDTO dto) throws Exception;

    void deleteUst(int u_seq) throws Exception;
}
