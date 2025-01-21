package backend.service;

import org.springframework.stereotype.Service;

import backend.domain.TokenInfo;
import backend.mapper.TokenInfoMapper;

@Service
public class TokenInfoService {
	
	private final TokenInfoMapper tokenInfoMapper;
	
	public TokenInfoService(TokenInfoMapper tokenInfoMapper) {
		this.tokenInfoMapper = tokenInfoMapper;
	}
	
	public void save(String loginId, String refreshToken) {
		TokenInfo tokenInfo = new TokenInfo();
		tokenInfo.setLoginId(loginId);
		tokenInfo.setRefreshToken(refreshToken);
		
		tokenInfoMapper.save(tokenInfo);
	}
	
	public TokenInfo findById(String loginId) {
		return tokenInfoMapper.findById(loginId);
	}
	
}
