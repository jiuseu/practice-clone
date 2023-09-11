package com.example.test.BoardService;

import com.example.test.dto.BoardDTO;
import com.example.test.dto.PageRequestDTO;
import com.example.test.dto.PageResponseDTO;
import com.example.test.service.BoardService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Test
    public void registerTest(){
        IntStream.rangeClosed(1,100).forEach(i ->{
            BoardDTO boardDTO = BoardDTO.builder()
                    .title("Title "+i)
                    .content("Content...."+i)
                    .user("USER"+i)
                    .build();
            boardService.register(boardDTO);
            log.info(boardDTO);
        });
    }

    @Test
    public void readTest(){
        Long bno = 10L;
        BoardDTO boardDTO = boardService.read(bno);
        log.info(boardDTO);
    }

    @Test
    public void modifyTest(){
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(100L)
                .title("UPDATE TITLE 100")
                .content("UPDATE CONTENT 100")
                .user("ghtrgiheogfeiougrbrtuik")
                .build();

        boardService.modify(boardDTO);
        log.info(boardDTO);
    }

    @Test
    public void removeTest(){
        Long bno = 1L;
        boardService.remove(bno);
    }

    @Test
    public void listTest(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .type("t")
                .keyword("1")
                .build();

        PageResponseDTO<BoardDTO> list = boardService.list(pageRequestDTO);
        log.info(list);
    }
}
