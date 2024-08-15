package com.hung.ming.svc.member;

import com.hung.ming.svc.member.command.EditCommand;
import com.hung.ming.svc.member.command.RegisterCommand;
import com.hung.ming.svc.member.command.UnRegisterCommand;
import com.hung.ming.svc.member.dto.MemberDto;
import com.hung.ming.svc.member.query.GetPageQuery;
import com.hung.ming.svc.member.query.GetQuery;
import org.springframework.data.domain.Page;

public interface IMemberSvc {
  Page<MemberDto> getMemberPage(GetPageQuery query);

  MemberDto getMember(GetQuery query);

  boolean register(RegisterCommand command);

  boolean unRegister(UnRegisterCommand command);

  boolean edit(EditCommand command);
}
