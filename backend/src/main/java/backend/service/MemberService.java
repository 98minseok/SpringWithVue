package backend.service;


import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import backend.domain.Member;
import backend.mapper.MemberMapper;
import backend.util.ApiResponse;

@Service
public class MemberService {
    private final MemberMapper memberMapper;

    
    
    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public List<Member> getAllMembers() {
        return memberMapper.findAll();
    }
    
    @Transactional
    public boolean insertMember(Member member) {
    	if(isExist(member)) {
    		return false;
    	}
    	else {
    		memberMapper.save(member);
    		return true;
    	}
    	
    }
    
    public void deleteMember(Long id) {
    	memberMapper.delete(id);
    }
    
    public Member findByLoginId(String id){
    	return memberMapper.findByLoginId(id);
    }
    
    public Member findByName(String name) {
    	return memberMapper.findByName(name);
    }
    
    public boolean isExist(Member member) {
    	return findByLoginId(member.getLoginId()) != null;
    }
    
    public boolean isVerified(Member member) {
    	return findByLoginId(member.getLoginId()).getPassword().equals(member.getPassword());
    	
    }
    
    public void updateMember(Member member) {
    	memberMapper.update(member);
    }
   
    
}
