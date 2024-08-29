package com.hung.ming.svc.member;

import com.github.pagehelper.PageInfo;
import com.hung.ming.svc.member.bean.MemberBean;
import com.hung.ming.svc.member.query.GetPageQuery;

public interface IMemberSvc {
  PageInfo<MemberBean> getMemberPage(GetPageQuery query);

  //  MemberBean getMember(GetQuery query);
  //
  //  boolean register(RegisterCommand command);
  //
  //  boolean unRegister(UnRegisterCommand command);
  //
  //  boolean edit(EditCommand command);
}
