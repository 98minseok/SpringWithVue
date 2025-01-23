package backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import backend.DTO.ReplyResponseDTO;
import backend.domain.Reply;
import backend.mapper.ReplyMapper;

@Service
public class ReplyService {
	
	ReplyMapper replyMapper;
	public ReplyService(ReplyMapper replyMapper) {
		// TODO Auto-generated constructor stub
		this.replyMapper = replyMapper;
	}
	
	public void save(Reply reply) {
		replyMapper.save(reply);
	}
	
	public List<ReplyResponseDTO> findByParentId(Long parentId){
		return replyMapper.findByParentId(parentId);
	}
	
	public void update(Reply reply) {
		replyMapper.update(reply);
	}
	
	public void delete(Long id) {
		replyMapper.delete(id);
	}
}
