package com.hung.ming.repo.common.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IBaseRepo<T, ID> extends JpaRepository<T, ID> {
}
