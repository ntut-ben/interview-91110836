package com.hung.ming.repo.member.repo;

import com.hung.ming.repo.member.entity.Member;
import com.hung.ming.repo.common.repo.IBaseRepo;
import org.springframework.stereotype.Repository;

@Repository
public interface IMemberRepo extends IBaseRepo<Member, String> {
  boolean existsByEmailOrUsername(String email, String username);

}
