package backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import backend.domain.Member;

@Mapper
public interface MemberMapper {
    List<Member> findAll();
    void save(Member memeber);
    Member findByLoginId(String id);
    Member findByName(String name);
    Member findById(Long id);
    void delete(Long id);
    void update(Member member);
}
	