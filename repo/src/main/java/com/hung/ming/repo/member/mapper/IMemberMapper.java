package com.hung.ming.repo.member.mapper;

import com.hung.ming.repo.common.IBaseMapper;
import com.hung.ming.repo.member.entity.Member;
import com.hung.ming.repo.member.sqlBuilder.MemberSqlBuilder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.logging.log4j.util.Lazy;

import java.util.List;
import java.util.Optional;

@Mapper
public interface IMemberMapper extends IBaseMapper {

  @SelectProvider(type = MemberSqlBuilder.class, method = "findAll")
  @ResultType(Member.class)
  List<Member> findAll();

  @Select("SELECT * FROM MEMBER WHERE id = #{id}")
  @ResultType(Member.class)
  Optional<Member> findById(String id);

  @Select("SELECT EXISTS(SELECT 1 FROM MEMBER WHERE email = #{email} OR username = #{username})")
  Boolean existsByEmailOrUsername(String email, String username);

  @Insert("""
      INSERT INTO MEMBER VALUES (#{id}, #{username}, #{paxx}, #{email}, #{fullName},
      #{dateOfBirth}, #{phoneNumber}, #{createdTime}, #{updateTime}, #{status})
      """)
  void save(Member member);
}
