package com.zetty01234.zettyyun.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class userDTO {

    private String id;
    private String pw;
    private boolean manager;
}
