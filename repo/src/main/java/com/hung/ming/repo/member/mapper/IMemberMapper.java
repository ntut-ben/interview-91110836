package com.hung.ming.repo.member.mapper;

import com.hung.ming.repo.common.IBaseMapper;
import com.hung.ming.repo.member.entity.Member;
import com.hung.ming.repo.member.sqlBuilder.MemberSqlBuilder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface IMemberMapper extends IBaseMapper {

  @SelectProvider(type = MemberSqlBuilder.class , method = "findAll")
  @ResultType(Member.class)
  List<Member> findAll();
}
