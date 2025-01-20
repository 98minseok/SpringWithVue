package backend;

import java.io.IOException;

import backend.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter implements jakarta.servlet.Filter{

	JwtUtil jwtUtil = new JwtUtil();
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
    		System.out.println("필터진입");
		// TODO Auto-generated method stub
		 	HttpServletRequest httpRequest = (HttpServletRequest) request;
	        HttpServletResponse httpResponse = (HttpServletResponse) response;
	        String authHeader = httpRequest.getHeader("Authorization");
	        System.out.println(authHeader);
	        if (authHeader != null && authHeader.startsWith("Bearer ")) {
	            String token = authHeader.substring(7);
	            System.out.println(token);
	            if (jwtUtil.validateToken(token)) {
	                chain.doFilter(request, response); // 토큰이 유효하면 다음 필터로 진행
	                return;
	            }
	        }
	        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 인증 실패
	}
	
}
