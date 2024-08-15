package com.hung.ming.repo.order.dao;

import com.hung.ming.repo.common.dao.IBaseDao;
import com.hung.ming.repo.order.dto.MemberOrderDto;
import com.hung.ming.repo.order.dto.ProductDto;
import com.hung.ming.repo.order.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import java.sql.Timestamp;
import java.util.List;

@NoRepositoryBean
public interface IOrderDao extends IBaseDao {
  List<MemberOrderDto> getMemberOrderStatistics(int count);

  Page<Order> getOrders(String orderNo, Timestamp orderCreateDate, String productName,
      Pageable pageable);

  List<ProductDto> getOrderProducts(List<String> orderIds);
}
