package com.hung.ming.svc.member;

import com.github.pagehelper.PageInfo;
import com.hung.ming.svc.member.bean.MemberBean;
import com.hung.ming.svc.member.command.EditCommand;
import com.hung.ming.svc.member.command.RegisterCommand;
import com.hung.ming.svc.member.command.UnRegisterCommand;
import com.hung.ming.svc.member.query.GetPageQuery;
import com.hung.ming.svc.member.query.GetQuery;

public interface IMemberSvc {
  PageInfo<MemberBean> getMemberPage(GetPageQuery query);

  MemberBean getMember(GetQuery query);

  boolean register(RegisterCommand command);

  boolean unRegister(UnRegisterCommand command);

  boolean edit(EditCommand command);
}
