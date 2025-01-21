package backend.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import backend.domain.TokenInfo;
import backend.service.MemberService;
import backend.service.TokenInfoService;
import backend.util.ApiResponse;
import backend.util.JwtUtil;

@RestController
public class MemberController {
    private final MemberService memberService;
    
    @Autowired
    private TokenInfoService tokenInfoService;		
    
    @Autowired
    private JwtUtil jwtUtil;	
    
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
    	return new ApiResponse(ApiResponse.SUCCESS, "Data를 성공적으로 가져왔습니다", memberService.findByLoginId(id));
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
    
    @PostMapping("/members/login")
    public ApiResponse loginMembers(@RequestBody Member member) {

    	if(!memberService.isExist(member)) return new ApiResponse(ApiResponse.FAILED,"아이디가 존재하지 않습니다.");
    	else if(!memberService.isVerified(member)) return new ApiResponse(ApiResponse.FAILED,"비밀번호가 틀렸습니다.");
    	
    	String loginId = member.getLoginId();
    	HashMap<String, String> data = generateTokens(loginId);
    	return new ApiResponse(ApiResponse.SUCCESS,"로그인에 성공하였습니다.",data);
    }
    
    @PostMapping("/api/refreshtoken")
    public ApiResponse refreshAccessToken(@RequestBody TokenInfo tokenInfo) {
    	
    	System.out.println("refreshtoken API 진입");
    	String requestToken = tokenInfo.getRefreshToken();	
    	String requestId = jwtUtil.getSubject(requestToken);
    	System.out.println("requestId : " + requestId);
    	
    	String savedToken = tokenInfoService.findById(requestId).getRefreshToken();
    	System.out.println("savedToken : " + savedToken);
    	try {
    		System.out.println("savedToken.equals(tokenInfoService.findById(requestId) = " + savedToken.equals(tokenInfoService.findById(requestId).getRefreshToken()));
    		if(savedToken.equals(tokenInfoService.findById(requestId).getRefreshToken())) {
        			HashMap<String, String> data = generateTokens(requestId);
        			return new ApiResponse(ApiResponse.SUCCESS,"토큰 갱신 성공",data);
        	}
    		else return new ApiResponse(ApiResponse.FAILED,"올바르지 않은 토큰");
    	}catch (Exception e) {
    		return new ApiResponse(ApiResponse.FAILED,"올바르지 않은 토큰");
    	}
    	
    }
    
    @GetMapping("/api/secure/test")
    public ApiResponse testToken() {
    	return new ApiResponse(ApiResponse.SUCCESS,"토큰을 사용한 접근에 성공");
    }
    
    private HashMap<String,String> generateTokens(String loginId) {
    	String token = jwtUtil.generateToken(loginId);
    	String refreshToken = jwtUtil.generateRefreshToken(loginId);
    	HashMap<String, String> data = new HashMap<>();
    	data.put("token", token);
    	data.put("refreshToken", refreshToken);
    	tokenInfoService.save(loginId, refreshToken);
    	return data;
    }
}
