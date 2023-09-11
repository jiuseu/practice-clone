package com.example.test.controller;

import com.example.test.dto.BoardDTO;
import com.example.test.dto.PageRequestDTO;
import com.example.test.dto.PageResponseDTO;
import com.example.test.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){

        log.info("list GET....");
        PageResponseDTO<BoardDTO> pageResponseDTO = boardService.list(pageRequestDTO);
        model.addAttribute("responseDTO",pageResponseDTO);
    }

    @GetMapping("/register")
    public void registerGet(){
    }

    @PostMapping("/register")
    public String registerPost(@Valid BoardDTO boardDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){

        log.info("register POST....");
        if(bindingResult.hasErrors()){
            log.info("register POST has ERROR...");
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());

            return "redirect:/board/register";
        }
        Long bno = boardService.register(boardDTO);
        redirectAttributes.addFlashAttribute("result",bno);

        return "redirect:/board/list";
    }

    @GetMapping({"/read","/modify"})
    public void readGet(Long bno,Model model,PageRequestDTO pageRequestDTO){
        log.info("read GET...........");
        BoardDTO boardDTO = boardService.read(bno);
        model.addAttribute("dto",boardDTO);
    }
}
