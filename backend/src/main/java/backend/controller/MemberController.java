package backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import backend.domain.Member;
import backend.service.MemberService;
import backend.util.ApiResponse;

@RestController
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members")
    public ApiResponse getMembers() {
        return new ApiResponse(ApiResponse.SUCCESS, "Data를 성공적으로 가져왔습니다.", memberService.getAllMembers());
    }
    
    @PostMapping("/members")
    public ApiResponse insertMember(@RequestBody Member member) {
        if(memberService.insertMember(member)) {
        	return new ApiResponse(ApiResponse.SUCCESS,"회원가입이 완료되었습니다.");
        }
        else {
        	return new ApiResponse(ApiResponse.FAILED,"회원가입에 실패했습니다.");
        }
    }
    
    @GetMapping("/members/name/{name}")
    public Member findByName(@PathVariable String name) {
    	return memberService.findByName(name);
    }
    
    @GetMapping("/members/id/{id}")
    public ApiResponse findById(@PathVariable String id) {
    	return new ApiResponse(ApiResponse.SUCCESS, "Data를 성공적으로 가져왔습니다", memberService.findById(id));
    }
    
    @DeleteMapping("/members")
    public ApiResponse deleteMember(@RequestParam Long id) {
    	memberService.deleteMember(id);
    	return new ApiResponse(ApiResponse.SUCCESS,"회원을 정상적으로 삭제했습니다.");
    }
    
    @PutMapping("/members")
    public ApiResponse updateMember(@RequestBody Member member) {
    	memberService.updateMember(member);
        return new ApiResponse(ApiResponse.SUCCESS,"정보 수정이 완료되었습니다.");
    }
}
