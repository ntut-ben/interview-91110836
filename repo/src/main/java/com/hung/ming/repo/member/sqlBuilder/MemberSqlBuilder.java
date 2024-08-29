package com.hung.ming.repo.member.sqlBuilder;

import com.hung.ming.repo.common.ISqlBuilder;
import org.apache.ibatis.jdbc.SQL;

public class MemberSqlBuilder implements ISqlBuilder {

  public String findAll() {
    return new SQL().SELECT("*").FROM("MEMBER").toString();
  }

}
