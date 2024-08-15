package com.hung.ming.repo.repo;

import com.hung.ming.repo.entity.Member;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepo extends IBaseRepo<Member, String> {
}
