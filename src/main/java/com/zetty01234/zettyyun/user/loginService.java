package com.zetty01234.zettyyun.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class loginService {

    @Autowired
    private loginMapper mapper;


    public userDTO loginProcess(String id, String pw) {
        userDTO dto = mapper.selectUserByLogin(id, pw);
        return dto;
    }
}
