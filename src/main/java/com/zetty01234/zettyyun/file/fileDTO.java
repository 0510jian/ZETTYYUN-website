package com.zetty01234.zettyyun.file;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class fileDTO {
    private int fSeq;
    private String oTable;
    private int oSeq;
    private String originalFilename;
    private String saveFilename;
    private String filePath;
    private int fileSize;
}
