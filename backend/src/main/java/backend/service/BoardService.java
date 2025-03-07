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
	
	public BoardResponseDTO findById(long id) {
		return boardMapper.findById(id);
	}
	
	public void hitBoard(long id) {
		boardMapper.hitBoard(id);
	}
	
	public void hitHeart(long id) {
		boardMapper.hitHeart(id);
	}
	
	public void edit(Board board) {
		boardMapper.edit(board);
	}
	
	public void delete(long id) {
		boardMapper.delete(id);
	}
}
