package backend.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import backend.domain.Reply;
import backend.service.ReplyService;
import backend.util.ApiResponse;

@RestController
public class ReplyController {

	ReplyService replyService;
	public ReplyController(ReplyService replyService) {
		// TODO Auto-generated constructor stub
		this.replyService = replyService;
	}
	
	@GetMapping("/reply/{parentId}")
	public ApiResponse getReply(@PathVariable Long parentId) {
		return new ApiResponse(ApiResponse.SUCCESS,"댓글을 성공적으로 가져왔습니다.",replyService.findByParentId(parentId));
	}
	
	@PostMapping("/api/secure/reply")
	public ApiResponse saveReply(@RequestBody Reply reply) {
		replyService.save(reply);
		return new ApiResponse(ApiResponse.SUCCESS,"댓글을 성공적으로 작성했습니다.");
	}
	
	@PutMapping("/api/secure/reply")
	public ApiResponse updateReply(@RequestBody Reply reply) {
		replyService.update(reply);
		return new ApiResponse(ApiResponse.SUCCESS,"댓글을 성공적으로 수정했습니다.");
	}
	
	@DeleteMapping("/reply/{id}")
	public ApiResponse deleteReply(@PathVariable Long id) {
		replyService.delete(id);
		return new ApiResponse(ApiResponse.SUCCESS,"댓글을 성공적으로 삭제했습니다.");
	}
	
}
