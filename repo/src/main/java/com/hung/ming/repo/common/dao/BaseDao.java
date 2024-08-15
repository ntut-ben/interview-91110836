package com.hung.ming.repo.common.dao;

import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.H2Templates;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Objects;

@NoRepositoryBean
public abstract class BaseDao extends QuerydslRepositorySupport implements IBaseDao {
  public BaseDao(Class<?> domainClass) {
    super(domainClass);
  }

  protected <T> JPASQLQuery<T> buildJPASQLQuery() {
    EntityManager em = getEntityManager();
    return new JPASQLQuery<>(Objects.requireNonNull(em), new H2Templates());
  }
}
