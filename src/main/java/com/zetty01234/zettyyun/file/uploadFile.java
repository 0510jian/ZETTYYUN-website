package com.zetty01234.zettyyun.file;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.UUID;

@Component
public class uploadFile {


    public fileDTO parseFile(String o_table, int o_seq, MultipartHttpServletRequest multipartHttpServletRequest, HttpServletRequest request) throws Exception {
        MultipartFile file = multipartHttpServletRequest.getFile("uploadfile");
        String uploadPath = request.getServletContext().getRealPath("/") + "WEB-INF/classes/static/upload/thumb/";

        createDir(uploadPath);

        String newFilename = UUID.randomUUID().toString();

        String originalFilename = file.getOriginalFilename();
        String fileExtension = getExtension(file);

        if(fileExtension == "") {
            return null;
        }

        fileDTO newDTO = new fileDTO();
        newDTO.setOSeq(o_seq);
        newDTO.setOTable(o_table);
        newDTO.setFileSize((int)file.getSize());
        newDTO.setOriginalFilename(originalFilename);
        newDTO.setSaveFilename(newFilename + fileExtension);
        newDTO.setFilePath("static/upload/thumb/" + newFilename + fileExtension);

        file.transferTo(new File(uploadPath + newFilename + fileExtension));

        return newDTO;
    }


    public void uploadImage(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
        String uploadPath = request.getServletContext().getRealPath("/") + "WEB-INF/classes/static/upload/ckeditor/";
        JSONObject json = new JSONObject();

        OutputStream out = null;
        PrintWriter printWriter = null;

        createDir(uploadPath);

        // 파일 가져오기
        MultipartFile file = multipartHttpServletRequest.getFile("upload");

        // 확장자 get
        String extension = getExtension(file);

        // 파일 체크
        if(file != null) {
            // 폴더 생성, 새 파일 이름
            String newFilename = UUID.randomUUID().toString();
            
            // 기존 파일 이름, 파일을 바이트 타입으로 변경
            String originalFilename = file.getOriginalFilename();
            byte[] bytes = file.getBytes();

            out = new FileOutputStream(new File(uploadPath+newFilename+extension));
            out.write(bytes);

            // 클라이언트에 이벤트?
            printWriter = response.getWriter();
            response.setContentType("text/html");

            // 파일 연결 주소 설정 - controller
            String filePath = "http://www.zetty-yun.com/static/upload/ckeditor/" + newFilename + extension;

            //json 설정
            json.append("uploaded", 1);
            json.append("fileName", originalFilename);
            json.append("url", filePath);
            printWriter.println(json);
        }
        if(out != null) out.close();
        if(printWriter != null) printWriter.close();
    }


    // 확장자 생성
    private String getExtension(MultipartFile file) {

        String contentType = file.getContentType();

        if (ObjectUtils.isEmpty(contentType)) {
            return "";
        } else {
            if(contentType.contains("image/jpeg")) {
                return ".jpg";
            } else if(contentType.contains("image/png")) {
                return ".png";
            } else if(contentType.contains("image/gif")) {
                return ".gif";
            } else {
                return "";
            }
        }
    }



    // 폴더 생성
    private void createDir(String uploadPath) {
        File dirPath = new File(uploadPath);
        if(dirPath.exists() == false) dirPath.mkdirs();
    }

}
