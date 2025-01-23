package backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import backend.DTO.ReplyResponseDTO;
import backend.domain.Reply;

@Mapper
public interface ReplyMapper {
	
	public void save(Reply reply);
	public List<ReplyResponseDTO> findByParentId(Long parentId);
	public void update(Reply reply);
	public void delete(Long id);
}
