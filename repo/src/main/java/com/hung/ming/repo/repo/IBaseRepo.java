package com.hung.ming.repo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IBaseRepo<T, ID> extends JpaRepository<T, ID> {
}
