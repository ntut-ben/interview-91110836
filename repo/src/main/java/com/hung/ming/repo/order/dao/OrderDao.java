package com.hung.ming.repo.order.dao;

import com.hung.ming.repo.common.dao.BaseDao;
import com.hung.ming.repo.member.entity.QMember;
import com.hung.ming.repo.order.dto.MemberOrderDto;
import com.hung.ming.repo.order.dto.ProductDto;
import com.hung.ming.repo.order.entity.Order;
import com.hung.ming.repo.order.entity.QOrder;
import com.hung.ming.repo.order.entity.QOrderProduct;
import com.hung.ming.repo.product.entity.QProduct;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.SubQueryExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.SimplePath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.sql.JPASQLQuery;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Repository
public class OrderDao extends BaseDao implements IOrderDao {
  private final JPAQueryFactory jpaQueryFactory;

  public OrderDao(@Autowired JPAQueryFactory jpaQueryFactory) {
    super(Order.class);
    this.jpaQueryFactory = jpaQueryFactory;
  }

  @Override
  public List<MemberOrderDto> getMemberOrderStatistics(int count) {
    JPASQLQuery<MemberOrderDto> query = this.buildJPASQLQuery();
    JPASQLQuery<MemberOrderDto> subQuery = this.buildJPASQLQuery();
    QMember qMember = QMember.member;
    QOrder qOrder = QOrder.order;

    StringPath memberId = Expressions.stringPath("memberId");
    NumberPath<Long> cnt = Expressions.numberPath(Long.class, "cnt");
    SimplePath<? extends SubQueryExpression> subQueryAlias = Expressions.path(
        subQuery.getClass(),
        "subQuery");

    subQuery.select(qOrder.memberId.as(memberId), qOrder.memberId.count().as(cnt))
        .from(qOrder)
        .groupBy(qOrder.memberId)
        .having(qOrder.memberId.count().gt(count));

    query.select(
            Projections.constructor(MemberOrderDto.class, qMember.id, qMember.username,
                qMember.email,
                qMember.fullName, qMember.dateOfBirth, qMember.phoneNumber, cnt))
        .from(qMember).join(subQuery, subQueryAlias).on(qMember.id.eq(memberId));


    return query.fetch();
  }

  @Override
  public Page<Order> getOrders(String orderNo, Timestamp orderCreateDate, String productName,
      Pageable pageable) {

    QProduct qProduct = QProduct.product;
    QOrder qOrder = QOrder.order;
    QOrderProduct qOrderProduct = QOrderProduct.orderProduct;

    JPAQuery<Order> orderQuery = jpaQueryFactory.selectDistinct(qOrder).from(qOrder)
        .leftJoin(qOrderProduct).on(qOrder.id.eq(qOrderProduct.id.orderId))
        .leftJoin(qProduct).on(qOrderProduct.id.productId.eq(qProduct.id));

    Predicate predicate = new BooleanBuilder();
    if (StringUtils.isNotEmpty(orderNo)) {
      predicate = ExpressionUtils.and(predicate, qOrder.id.eq(orderNo));
    }

    if (ObjectUtils.isNotEmpty(orderCreateDate)) {
      final String formatter = "yyyy-MM-dd HH:mm:ss";
      String orderCreateDateStr = new SimpleDateFormat(formatter).format(orderCreateDate);
      predicate = ExpressionUtils.and(predicate,
          Expressions.stringTemplate(String.format("FORMATDATETIME({0}, '%s')", formatter),
              qOrder.orderDate).eq(orderCreateDateStr));
    }

    if (StringUtils.isNotEmpty(productName)) {
      predicate = ExpressionUtils.and(predicate, qProduct.name.eq(productName));
    }

    orderQuery = orderQuery.where(predicate);
    Long total = orderQuery.fetchCount();

    JPQLQuery<Order> query = getQuerydsl().applyPagination(pageable, orderQuery);
    List<Order> result = query.fetch();
    return PageableExecutionUtils.getPage(result, pageable, () -> total);
  }

  @Override
  public List<ProductDto> getOrderProducts(List<String> orderIds) {
    QProduct qProduct = QProduct.product;

    QOrderProduct qOrderProduct = QOrderProduct.orderProduct;

    return jpaQueryFactory.select(
            Projections.constructor(ProductDto.class, qOrderProduct.id.orderId, qProduct.name,
                qProduct.description, qOrderProduct.quantity)).from(qOrderProduct)
        .join(qProduct).on(qProduct.id.eq(qOrderProduct.id.productId))
        .where(qOrderProduct.id.orderId.in(orderIds))
        .fetch();
  }
}
