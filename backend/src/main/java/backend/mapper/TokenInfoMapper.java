package backend.mapper;

import org.apache.ibatis.annotations.Mapper;

import backend.domain.TokenInfo;

@Mapper
public interface TokenInfoMapper {
	
	public void save(TokenInfo tokenInfo);
	
	public TokenInfo findById(String loginId);
}
