package com.zetty01234.zettyyun.ust;

import com.zetty01234.zettyyun.user.userDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class ustController {

    @Autowired
    private ustService service;

    // UST 목록 선택
    @GetMapping("/ust")
    public ModelAndView selectUst(HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView("/ust/ust.html");

        ArrayList<ustDTO> ustList = service.selectUstList();
        mv.addObject("ustList", ustList);

        HttpSession session = request.getSession();
        if(session != null){
            userDTO res = (userDTO) session.getAttribute("res");
            mv.addObject("res", res);
        }

        return mv;
    }
    
    // UST 추가
    @PostMapping("/ust")
    public String insertUst(ustDTO dto, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        if(session != null) service.insertUst(dto);
        return "redirect:/ust";
    }

    // UST 삭제
    @RequestMapping("/ust/{uSeq}")
    public String deleteUst(@PathVariable(value = "uSeq") int u_seq,
                            HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        if(session != null) service.deleteUst(u_seq);
        return "redirect:/ust";
    }
}
