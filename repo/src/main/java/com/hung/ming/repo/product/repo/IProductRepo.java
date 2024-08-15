package com.hung.ming.repo.product.repo;

import com.hung.ming.repo.common.repo.IBaseRepo;
import com.hung.ming.repo.product.entity.Product;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepo extends IBaseRepo<Product, String> {

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Query("SELECT p FROM Product p WHERE p.id = :productId")
  Product findByIdWithLock(String productId);
}
