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
    	if(isDuplicate(member)) {
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
    
    public Member findById(String id){
    	return memberMapper.findById(id);
    }
    
    public Member findByName(String name) {
    	return memberMapper.findByName(name);
    }
    
    private boolean isDuplicate(Member member) {
    	return findById(member.getLoginId()) != null;
    }
    
    public void updateMember(Member member) {
    	memberMapper.update(member);
    }
    
}
