package com.hung.ming.repo.order.repo;

import com.hung.ming.repo.common.repo.IBaseRepo;
import com.hung.ming.repo.order.entity.OrderProduct;
import com.hung.ming.repo.order.entity.OrderProductId;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IOrderProductRepo extends IBaseRepo<OrderProduct, OrderProductId> {

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Query(
      "SELECT p FROM OrderProduct p WHERE p.id = :orderProductId")
  Optional<OrderProduct> findByIdWithLock(OrderProductId orderProductId);
}
