package backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import backend.DTO.BoardResponseDTO;
import backend.domain.Board;
import backend.mapper.BoardMapper;

@Service
public class BoardService {

	private final BoardMapper boardMapper;
	
	public BoardService(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}
	
	public void save(Board board) {
		boardMapper.save(board);
	}
	
	public List<BoardResponseDTO> findAll(){
		return boardMapper.findAll();
	}
}
