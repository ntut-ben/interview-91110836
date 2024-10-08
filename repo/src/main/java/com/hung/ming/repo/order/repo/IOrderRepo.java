package com.hung.ming.repo.order.repo;

import com.hung.ming.repo.common.repo.IBaseRepo;
import com.hung.ming.repo.order.entity.Order;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepo extends IBaseRepo<Order, String> {

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Query("SELECT p FROM Order p WHERE p.id = :orderId")
  Order findByIdWithLock(String orderId);
}
