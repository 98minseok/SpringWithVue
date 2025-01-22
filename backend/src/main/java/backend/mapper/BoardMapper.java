package backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import backend.DTO.BoardResponseDTO;
import backend.domain.Board;

@Mapper
public interface BoardMapper {
	
	public void save(Board board);
	public List<BoardResponseDTO> findAll();
	public BoardResponseDTO findById(long id);
	public void edit(Board board);
	public void hitBoard(long id);
	public void hitHeart(long id);
	public void delete(long id);
}
