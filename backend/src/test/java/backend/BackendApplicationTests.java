package backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import backend.util.JwtUtil;

@SpringBootTest
class BackendApplicationTests {

	@Test
	void contextLoads() {
		JwtUtil jwtUtil = new JwtUtil();
	    String loginId = "testUser";
	    
	    // 토큰 생성
	    String token = jwtUtil.generateToken(loginId);
	    System.out.println("Generated Token: " + token);

	    // 토큰 검증
	    boolean isValid = jwtUtil.validateToken(token);
	    System.out.println("Is Token Valid: " + isValid);  // true여야 합니다.
	}

}
