package com.hung.ming.svc.member;

import com.hung.ming.repo.member.entity.Member;
import com.hung.ming.repo.member.repo.IMemberRepo;
import com.hung.ming.repo.util.PropertyUtilsProxy;
import com.hung.ming.svc.member.bean.MemberBean;
import com.hung.ming.svc.member.command.EditCommand;
import com.hung.ming.svc.member.command.RegisterCommand;
import com.hung.ming.svc.member.command.UnRegisterCommand;
import com.hung.ming.svc.member.query.GetPageQuery;
import com.hung.ming.svc.member.query.GetQuery;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@AllArgsConstructor
public class MemberSvc implements IMemberSvc {

  private final IMemberRepo memberRepo;

  private final static String ACTIVE = "ACTIVE";

  @Override
  public Page<MemberBean> getMemberPage(GetPageQuery query) {
    Page<Member> memberPage = memberRepo.findAll(query.getPageable());

    if (memberPage.hasContent()) {
      return memberPage.map(entity -> {
        MemberBean bean = new MemberBean();
        PropertyUtilsProxy.copyProperties(bean, entity);
        return bean;
      });
    }

    return Page.empty();
  }

  @Override
  public MemberBean getMember(GetQuery query) {
    return memberRepo.findById(query.getId()).map(entity -> {
      MemberBean dto = new MemberBean();
      PropertyUtilsProxy.copyProperties(dto, entity);
      return dto;
    }).orElse(null);
  }

  @Transactional
  @Override
  public boolean register(RegisterCommand command) {
    boolean isRegister = false;
    Member member = new Member();
    Timestamp now = new Timestamp(System.currentTimeMillis());
    PropertyUtilsProxy.copyProperties(member, command);
    member.setId(UUID.randomUUID().toString());
    member.setStatus(ACTIVE);
    member.setCreatedTime(now);
    member.setUpdateTime(now);

    if (BooleanUtils.isFalse(
        memberRepo.existsByEmailOrUsername(command.getEmail(), command.getUsername()))) {
      memberRepo.save(member);
      isRegister = true;
    }

    return isRegister;
  }

  @Transactional
  @Override
  public boolean unRegister(UnRegisterCommand command) {
    boolean isDelete = false;
    if (memberRepo.existsById(command.getId())) {
      memberRepo.deleteById(command.getId());
      isDelete = true;
    }
    return isDelete;
  }

  @Transactional
  @Override
  public boolean edit(EditCommand command) {
    AtomicBoolean isEdit = new AtomicBoolean(false);
    Optional<Member> memberOptional = memberRepo.findById(command.getId());

    memberOptional.ifPresent(member -> {
      PropertyUtilsProxy.copyProperties(member, command);
      member.setUpdateTime(new Timestamp(System.currentTimeMillis()));
      memberRepo.save(member);
      isEdit.set(true);
    });

    return isEdit.get();
  }
}
