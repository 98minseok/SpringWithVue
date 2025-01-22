package backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import backend.domain.Board;
import backend.mapper.BoardMapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

@SpringBootTest
class BoardServiceTest {

    @Autowired
    private BoardService boardService; // 실제 Service Bean

    @Test
    void findAllTest() {
    	Board board = new Board();
    	board.setContent("TEST CONTENT");
    	board.setTitle("TEST TITLE");
    	board.setUserId("als981209");
    	
    	boardService.save(board);
    	
    	assertThat(boardService.findAll().get(0).getTitle()).isEqualTo(board.getTitle());
    }
}