package backend.util;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


public class JwtUtil {
    
	private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private final long expirationTime = 3600000;
	
	public String generateToken(String loginId) {
		return Jwts.builder()
				.setSubject(loginId)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expirationTime))
				.signWith(key)
				.compact();
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJwt(token);
			return true;
		}
		catch (Exception e){
			return false;
		}		
	}
}
