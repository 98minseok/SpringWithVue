package backend.util;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	
	private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private final long expirationTime = 1000 * 60 * 30;
	
	public String generateToken(String loginId) {
		return Jwts.builder()
				.setSubject(loginId)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expirationTime))
				.signWith(key,SignatureAlgorithm.HS256)
				.compact();
	}
	
	public String generateRefreshToken(String loginId) {
		return Jwts.builder()
				.setSubject(loginId)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expirationTime * 12))
				.signWith(key,SignatureAlgorithm.HS256)
				.compact();
	}
	
	public String getSubject(String token) {
		try {
			System.out.println(token);
			return Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token)
			.getBody()
			.getSubject();
		}
		catch (ExpiredJwtException e) {
			e.printStackTrace();
			return "#";
		}
		catch (Exception e){
			e.printStackTrace();
			return "#";
		}
	}
	
	public boolean validateToken(String token) {
		try {
			System.out.println(token);
			Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token);
			return true;
		}
		catch (ExpiredJwtException e) {
			return false;
		}
		catch (Exception e){
			return false;
		}		
	}
}
