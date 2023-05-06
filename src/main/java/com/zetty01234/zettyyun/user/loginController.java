package com.zetty01234.zettyyun.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class loginController {
    @Autowired
    private loginService service;

    @GetMapping("/login")
    public ModelAndView login(HttpServletRequest request) {

        ModelAndView mv = new ModelAndView("/login/login.html");

        HttpSession httpSession = request.getSession();
        if(httpSession != null) {
            userDTO res = (userDTO) httpSession.getAttribute("res");
            mv.addObject("res", res);
        }

        return mv;
    }

    @PostMapping("/loginProcess")
    public String loginProcess(@RequestParam("id") String id, @RequestParam("pw") String pw,
                               HttpServletRequest request) {
        userDTO dto = service.loginProcess(id, pw);

        if(dto != null) {
            HttpSession httpSession = request.getSession();
            httpSession.setMaxInactiveInterval(60 * 60 * 1); // 1시간
            httpSession.setAttribute("res", dto);
        }

        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession httpSession = request.getSession(false);
        if(httpSession != null){
            httpSession.invalidate();
        }
        return "redirect:/";
    }
}
