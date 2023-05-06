package com.zetty01234.zettyyun.vb;

import com.zetty01234.zettyyun.file.uploadFile;
import com.zetty01234.zettyyun.user.userDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@Controller
public class vbController {
    @Autowired
    private vbService service;
    @Autowired
    private uploadFile uploadFile;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    // 음원 목록 조회
    @GetMapping("/vb")
    public ModelAndView selectVbList(HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView("/vb/vb.html");

        ArrayList<vbDTO> vbList = service.selectVbList();

        for(vbDTO vb : vbList) {
            try {
                vb.setVTpath(service.selectThumb(vb.getVSeq()));
            }catch (IndexOutOfBoundsException e) {
                break;
            }
        }

        mv.addObject("vbList", vbList);

        // session
        HttpSession session = request.getSession();
        if(session != null) {
            userDTO res = (userDTO) session.getAttribute("res");
            mv.addObject("res", res);
        }

        return mv;
    }

    // 음원 등록 페이지
    @GetMapping("/vb/write")
    public ModelAndView goVbWrite(HttpServletRequest request) throws Exception {
        ModelAndView mv;
        HttpSession session = request.getSession();

        if(session != null) {
            mv = new ModelAndView("/vb/vbWrite");
            userDTO res = (userDTO) session.getAttribute("res");
            mv.addObject("res", res);
        } else {
            mv = new ModelAndView("/vb");
        }
        return mv;

    }

    // 음원 등록
    @PostMapping("/vb/write")
    public String insertVb(@ModelAttribute vbDTO dto, MultipartHttpServletRequest multipartHttpServletRequest,
                           HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        if(session != null) {
            service.insertVb(dto, multipartHttpServletRequest, request);
        }
        return "redirect:/vb";
    }


    // textarea 이미지 업로드
    @PostMapping("/vb/uploadImage")
    public void uploadImage(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
        HttpSession session = request.getSession();
        if(session != null) {
            uploadFile.uploadImage(request, response, multipartHttpServletRequest);

        }
    }


    // 음원 조회
    @GetMapping("/vb/{vLink}")
    public ModelAndView selectVb(@PathVariable(value = "vLink") String v_link,
                                 HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView("/vb/" + v_link + ".html");

        vbDTO dto = service.selectVb(v_link);
        mv.addObject("dto", dto);

        HttpSession session = request.getSession();
        if(session != null) {
            userDTO res = (userDTO) session.getAttribute("res");
            mv.addObject("res", res);
        }

        return mv;
    }

    // 음원 수정 페이지
    @GetMapping("/vb/modify/{vLink}")
    public ModelAndView modifyVb(@PathVariable(value = "vLink") String v_link,
                                 HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView("/vb/vbModify.html");

        HttpSession session = request.getSession();
        if(session != null) {
            vbDTO dto = service.selectVb(v_link);
            mv.addObject("dto", dto);

            userDTO res = (userDTO) session.getAttribute("res");
            mv.addObject("res", res);
        }

        return mv;
    }

    // 음원 수정
    @PutMapping("/vb/{vLink}")
    public String updateVb(@PathVariable(value = "vLink") String v_link, vbDTO newDto, MultipartHttpServletRequest multipartHttpServletRequest,
                           HttpServletRequest request) throws Exception {
        vbDTO dto = service.selectVb(v_link);
        HttpSession session = request.getSession();

        if(session != null) {
            service.updateVb(newDto);
            System.out.println(dto);
            if ((multipartHttpServletRequest.getFile("uploadfile").getSize()) != 0) {
                Path filePath = Paths.get(request.getServletContext().getRealPath("/") + "WEB-INF/classes/" + service.selectFilePath("vb", dto.getVSeq()));
                try {
                    Files.deleteIfExists(filePath);
                } catch (Exception e) {
                    logger.info("파일이 존재하지 않습니다");
                }
                service.updateFile(dto.getVSeq(), multipartHttpServletRequest, request);
            }
        }

        return "redirect:/vb/" + dto.getVLink();
    }

    // 음원 삭제
    @DeleteMapping("/vb/{vLink}")
    public String deleteVb(@PathVariable(value = "vLink") String v_link,
                           HttpServletRequest request) throws Exception {

        HttpSession session = request.getSession();
        if (session != null) {
            vbDTO dto = service.selectVb(v_link);

            Path filePath = Paths.get(request.getServletContext().getRealPath("/") + "WEB-INF/classes/" + service.selectFilePath("vb", dto.getVSeq()));
            try {
                Files.deleteIfExists(filePath);
            } catch (Exception e) {
                logger.info("파일이 존재하지 않습니다");
            }

            service.deleteFile("vb", dto.getVSeq());
            service.deleteVb(v_link);
        }

        return "redirect:/vb";
    }
}
