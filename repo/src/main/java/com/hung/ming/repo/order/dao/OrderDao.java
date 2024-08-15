package com.hung.ming.repo.order.dao;

import com.hung.ming.repo.common.dao.BaseDao;
import com.hung.ming.repo.member.entity.QMember;
import com.hung.ming.repo.order.dto.MemberOrderDto;
import com.hung.ming.repo.order.entity.Order;
import com.hung.ming.repo.order.entity.QOrder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.SubQueryExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.SimplePath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.sql.JPASQLQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDao extends BaseDao implements IOrderDao {
  public OrderDao() {
    super(Order.class);
  }

  public OrderDao(Class<?> domainClass) {
    super(domainClass);
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
}
