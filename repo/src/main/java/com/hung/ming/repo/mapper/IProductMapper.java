package com.hung.ming.repo.mapper;

import com.hung.ming.repo.common.IBaseMapper;
import com.hung.ming.repo.dto.ProductDto;
import com.hung.ming.repo.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IProductMapper extends IBaseMapper {

  Product findByIdWithLock(String productId);

  int save(Product product);

  List<ProductDto> getProductByOrderId(List<String> orderIds);
}
