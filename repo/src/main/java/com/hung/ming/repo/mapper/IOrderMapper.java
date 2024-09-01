package com.hung.ming.repo.mapper;

import com.hung.ming.repo.common.IBaseMapper;
import com.hung.ming.repo.dto.MemberOrderDto;
import com.hung.ming.repo.entity.Order;
import com.hung.ming.repo.query.QGetOrderQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IOrderMapper extends IBaseMapper {
  List<MemberOrderDto> getMemberOrderStatistics(int count);

  List<Order> getOrders(QGetOrderQuery query);

  Order findByIdWithLock(String orderId);

  int save(Order order);

  int update(Order order);
}
