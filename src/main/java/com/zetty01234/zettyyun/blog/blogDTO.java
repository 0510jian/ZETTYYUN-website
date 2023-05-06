package com.zetty01234.zettyyun.blog;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class blogDTO {

    private int bSeq; // 블로그 게시글 번호
    private String bTitle; // 블로그 게시글 타이틀
    private String bContent; // 블로그 게시글 내용
    private String bDatetime; // 블로그 게시글 게시날짜, 시간
    private boolean bHome; // home 설정 여부
}
