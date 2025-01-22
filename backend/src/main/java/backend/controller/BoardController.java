package backend.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import backend.domain.Board;
import backend.service.BoardService;
import backend.util.ApiResponse;

@RestController
public class BoardController {
	
	BoardService boardService;
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@PostMapping("/api/secure/board")
	public ApiResponse saveBoard(@RequestBody Board board) {
		boardService.save(board);
		return new ApiResponse(ApiResponse.SUCCESS, "게시글 저장에 성공했습니다.");
	}
	
	@PutMapping("/api/secure/board")
	public ApiResponse editBoard(@RequestBody Board board) {
		boardService.edit(board);
		return new ApiResponse(ApiResponse.SUCCESS, "게시글 수정에 성공했습니다.");
	}
	
	@GetMapping("board")
	public ApiResponse getAllBoard() {
		
		return new ApiResponse(ApiResponse.SUCCESS,"게시글을 성공적으로 불러왔습니다.",boardService.findAll());
	}
	
	@GetMapping("board/{id}")
	public ApiResponse getBoardById(@PathVariable long id) {
		boardService.hitBoard(id);
		return new ApiResponse(ApiResponse.SUCCESS,"게시글을 성공적으로 불러왔습니다.",boardService.findById(id));
	}
	
	@PostMapping("board/{id}")
	public ApiResponse hitHeart(@PathVariable long id) {
				boardService.hitHeart(id);
				return new ApiResponse(ApiResponse.SUCCESS,"좋아요 눌러짐 ! ");
	}
	
	@DeleteMapping("board/{id}")
	public ApiResponse deleteBoard(@PathVariable long id) {
		boardService.delete(id);
		return new ApiResponse(ApiResponse.SUCCESS,"삭제됨");
}

}
