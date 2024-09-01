package com.hung.ming.repo.mapper;

import com.hung.ming.repo.command.member.QEditCommand;
import com.hung.ming.repo.common.IBaseMapper;
import com.hung.ming.repo.entity.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface IMemberMapper extends IBaseMapper {
  List<Member> findAll();

  Optional<Member> findById(String id);

  Boolean existsByEmailOrUsername(String email, String username);

  int save(Member member);

  int deleteById(String id);

  int updateById(QEditCommand command);
}
