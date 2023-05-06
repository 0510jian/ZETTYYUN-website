package com.zetty01234.zettyyun.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class contactController {

    @Autowired
    private contactService service;

    @GetMapping("/contact")
    public String goContact() throws Exception {
        return "/contact/contact.html";
    }

    @PostMapping("/contact")
    public String send(contactDTO dto) throws Exception {
        String message = "From. " + dto.getEmail() + "\n"
                + "Input. *" + dto.getInput() + "*\n"
                + "Title. " + dto.getTitle() + "\n"
                + dto.getMessage();
        String title = "❤blog: " + dto.getTitle();

        if(dto.getInput().equals("")) {
            service.sendEmail(title, message);
        }

        return "redirect:/contact";
    }

}
