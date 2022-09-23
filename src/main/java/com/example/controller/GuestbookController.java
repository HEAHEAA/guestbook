package com.example.controller;

import com.example.dto.GuestbookDTO;
import com.example.dto.PageRequestDTO;
import com.example.entity.Guestbook;
import com.example.repository.GuestbookRepository;
import com.example.service.GuestbookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@Log4j2
@RequestMapping("/guestbook")
@RequiredArgsConstructor
public class GuestbookController {

    private final GuestbookService service;
    private final GuestbookRepository repository;

    @GetMapping({"/"})
    public String index(){
        return "redirect:/guestbook/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        log.info("list......................" + pageRequestDTO);
        model.addAttribute("result", service.getList(pageRequestDTO));

    }


    //------------------------------------------------

    @GetMapping("/register")
    public void register(){
        log.info("register get...");
    }

    @PostMapping("/register")
    public String registerPost(GuestbookDTO dto, RedirectAttributes redirectAttributes){
        log.info("dto..." + dto);
        long gno = service.register(dto);

        redirectAttributes.addFlashAttribute("mdg" , gno);
        return "redirect:/guestbook/list";
    }

    @GetMapping("/read")
    public void read(long gno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model mOdel){
        log.info("gno: " + gno);
        GuestbookDTO dto = service.read(gno);
        mOdel.addAttribute("dto" , dto);
    }
}
