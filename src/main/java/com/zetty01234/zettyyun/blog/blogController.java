package com.zetty01234.zettyyun.blog;

import com.zetty01234.zettyyun.file.uploadFile;
import com.zetty01234.zettyyun.user.userDTO;
import com.zetty01234.zettyyun.vb.vbDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class blogController {
    @Autowired
    private blogService service;

    @Autowired
    private uploadFile uploadFile;

    @GetMapping("/")
    public ModelAndView goHome(HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView("/blog/home.html");

        ArrayList<blogDTO> dto = service.selectHome();
        mv.addObject("dto", dto);

        //session
        HttpSession httpSession = request.getSession();
        if(httpSession != null) {
            userDTO res = (userDTO) httpSession.getAttribute("res");
            mv.addObject("res", res);
        }

        return mv;
    }

    @GetMapping("/blog")
    public ModelAndView goBlog(HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView("/blog/blog.html");

        ArrayList<blogDTO> blogList = service.selectBlogList();
        mv.addObject("blogList", blogList);

        //session
        HttpSession httpSession = request.getSession();
        if(httpSession != null) {
            userDTO res = (userDTO) httpSession.getAttribute("res");
            mv.addObject("res", res);
        }

        return mv;
    }

    @GetMapping("/blog/write")
    public ModelAndView goWriteBlog(HttpServletRequest request) throws Exception {
        ModelAndView mv;
        HttpSession session = request.getSession();

        if(session != null) {
            mv = new ModelAndView("/blog/blogWrite.html");
            userDTO res = (userDTO) session.getAttribute("res");
            mv.addObject("res", res);
        } else {
            mv = new ModelAndView("/blog");
        }
        return mv;
    }

    @PostMapping("/blog/write")
    public String insertBlog(blogDTO dto, HttpServletRequest request) throws Exception {

        //session
        HttpSession httpSession = request.getSession();
        if(httpSession != null) service.insertBlog(dto);

        return "redirect:/blog";
    }

    // textarea 이미지 업로드
    @PostMapping("/blog/uploadImage")
    public void uploadImage(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
        HttpSession session = request.getSession();
        if(session != null) uploadFile.uploadImage(request, response, multipartHttpServletRequest);
    }

    @GetMapping("/blog/{bSeq}")
    public ModelAndView detailBlog(@PathVariable(value = "bSeq") int b_seq,
                                   HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView("/blog/blogDetail.html");

        blogDTO dto = service.selectBlog(b_seq);
        mv.addObject("dto", dto);

        //session
        HttpSession httpSession = request.getSession();
        if(httpSession != null) {
            userDTO res = (userDTO) httpSession.getAttribute("res");
            mv.addObject("res", res);
        }
        return mv;
    }

    @GetMapping("/blog/modify/{bSeq}")
    public ModelAndView modifyBlog(@PathVariable(value = "bSeq") int b_seq,
                                   HttpServletRequest request) throws Exception {
        //session
        HttpSession httpSession = request.getSession();
        if(httpSession != null) {
            ModelAndView mv = new ModelAndView("/blog/blogModify.html");

            blogDTO dto = service.selectBlog(b_seq);
            mv.addObject("dto", dto);

            userDTO res = (userDTO) httpSession.getAttribute("res");
            mv.addObject("res", res);

            return mv;
        } else {
            ModelAndView mv = new ModelAndView("/");
            return mv;
        }
    }

    @PutMapping("/blog/{bSeq}")
    public String updateBlog(blogDTO dto, HttpServletRequest request) throws Exception {
        HttpSession httpSession = request.getSession();
        if(httpSession != null) {
            service.updateBlog(dto);
            return "redirect:/blog/" + dto.getBSeq();
        }
        return "redirect:/blog";
    }

    @DeleteMapping("/blog/{bSeq}")
    public String deleteBlog(@PathVariable(value = "bSeq") int b_seq,
                             HttpServletRequest request) throws Exception {
        HttpSession httpSession = request.getSession();
        if(httpSession != null) service.deleteBlog(b_seq);

        return "redirect:/blog";
    }
}
