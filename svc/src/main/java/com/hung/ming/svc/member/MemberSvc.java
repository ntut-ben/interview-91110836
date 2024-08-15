package com.hung.ming.svc.member;

import com.hung.ming.repo.entity.Member;
import com.hung.ming.repo.repo.IMemberRepo;
import com.hung.ming.svc.member.command.EditCommand;
import com.hung.ming.svc.member.command.RegisterCommand;
import com.hung.ming.svc.member.command.UnRegisterCommand;
import com.hung.ming.svc.member.dto.MemberDto;
import com.hung.ming.svc.member.query.GetPageQuery;
import com.hung.ming.svc.member.query.GetQuery;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MemberSvc implements IMemberSvc {

  private final IMemberRepo memberRepo;

  @Override
  public Page<MemberDto> getMemberPage(GetPageQuery query) {
    Page<Member> memberPage = memberRepo.findAll(query.getPageable());
    List<MemberDto> memberDtos = memberPage.getContent().stream().map(entity -> {
      MemberDto dto = new MemberDto();
      BeanUtils.copyProperties(entity, dto);
      return dto;
    }).toList();
    return PageableExecutionUtils.getPage(memberDtos, memberPage.getPageable(),
        memberPage::getTotalElements);
  }

  @Override
  public MemberDto getMember(GetQuery query) {
    return null;
  }

  @Override
  public boolean register(RegisterCommand command) {
    return false;
  }

  @Override
  public boolean unRegister(UnRegisterCommand command) {
    return false;
  }

  @Override
  public boolean edit(EditCommand command) {
    return false;
  }
}
