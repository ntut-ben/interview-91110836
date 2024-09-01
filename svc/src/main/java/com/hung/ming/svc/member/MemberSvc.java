package com.hung.ming.svc.member;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hung.ming.repo.command.member.QEditCommand;
import com.hung.ming.repo.entity.Member;
import com.hung.ming.repo.mapper.IMemberMapper;
import com.hung.ming.repo.util.PropertyUtilsProxy;
import com.hung.ming.svc.member.bean.MemberBean;
import com.hung.ming.svc.member.command.EditCommand;
import com.hung.ming.svc.member.command.RegisterCommand;
import com.hung.ming.svc.member.command.UnRegisterCommand;
import com.hung.ming.svc.member.query.GetPageQuery;
import com.hung.ming.svc.member.query.GetQuery;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MemberSvc implements IMemberSvc {

  private final IMemberMapper memberMapper;

  private final static String ACTIVE = "ACTIVE";

  @Override
  public PageInfo<MemberBean> getMemberPage(GetPageQuery query) {
    var pageable = query.getPageable();
    PageInfo<MemberBean> memberBeanPage = PageInfo.emptyPageInfo();

    // startPage 的下一個查詢會自動進行分頁
    PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
    List<Member> members = memberMapper.findAll();
    PageInfo<Member> page = new PageInfo<>(members);

    if (page.hasContent()) {
      memberBeanPage = page.convert(entity -> {
        MemberBean bean = new MemberBean();
        PropertyUtilsProxy.copyProperties(bean, entity);
        return bean;
      });
    }

    return memberBeanPage;
  }

  @Override
  public MemberBean getMember(GetQuery query) {
    return memberMapper.findById(query.getId()).map(entity -> {
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
        memberMapper.existsByEmailOrUsername(command.getEmail(), command.getUsername()))) {
      int count = memberMapper.save(member);
      isRegister = count > 0;
    }

    return isRegister;
  }

  @Transactional
  @Override
  public boolean unRegister(UnRegisterCommand command) {
    int count = memberMapper.deleteById(command.getId());
    return count > 0;
  }

  @Transactional
  @Override
  public boolean edit(EditCommand command) {
    QEditCommand editCommand = new QEditCommand();
    PropertyUtilsProxy.copyProperties(editCommand, command);
    int count = memberMapper.updateById(editCommand);
    return count > 0;
  }

}
