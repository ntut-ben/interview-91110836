package com.hung.ming.repo.mapper;

import com.hung.ming.repo.common.IBaseMapper;
import com.hung.ming.repo.entity.OrderProduct;
import com.hung.ming.repo.entity.OrderProductId;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface IOrderProductMapper extends IBaseMapper {
  Optional<OrderProduct> findByIdWithLock(OrderProductId orderProductId);

  int update(OrderProduct orderProduct);

  int save(OrderProduct orderProduct);
}
