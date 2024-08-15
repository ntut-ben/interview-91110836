package com.hung.ming.repo.order.repo;

import com.hung.ming.repo.common.repo.IBaseRepo;
import com.hung.ming.repo.order.entity.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepo extends IBaseRepo<Order, String> {
}
