package com.hung.ming.repo.repo;

import com.hung.ming.repo.entity.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepo extends IBaseRepo<Order, String> {
}
