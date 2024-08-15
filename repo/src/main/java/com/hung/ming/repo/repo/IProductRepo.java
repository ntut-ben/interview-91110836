package com.hung.ming.repo.repo;

import com.hung.ming.repo.entity.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepo extends IBaseRepo<Product, String> {
}
