package com.hung.ming.repo.order.dao;

import com.hung.ming.repo.common.dao.IBaseDao;
import com.hung.ming.repo.order.dto.MemberOrderDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface IOrderDao extends IBaseDao {
  List<MemberOrderDto> getMemberOrderStatistics(int count);
}
