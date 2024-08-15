package com.hung.ming.rest.member;

import com.hung.ming.rest.member.req.EditReq;
import com.hung.ming.rest.member.req.GetPageReq;
import com.hung.ming.rest.member.req.GetReq;
import com.hung.ming.rest.member.req.RegisterReq;
import com.hung.ming.rest.member.req.UnRegisterReq;
import com.hung.ming.rest.member.resp.MemberVo;
import com.hung.ming.svc.member.IMemberSvc;
import com.hung.ming.svc.member.command.EditCommand;
import com.hung.ming.svc.member.command.RegisterCommand;
import com.hung.ming.svc.member.command.UnRegisterCommand;
import com.hung.ming.svc.member.dto.MemberDto;
import com.hung.ming.svc.member.query.GetPageQuery;
import com.hung.ming.svc.member.query.GetQuery;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberCtrl {

  private final IMemberSvc memberSvc;

  @Operation(description = "取得會員資料分頁")
  @PostMapping("/getMemberPage")
  public Page<MemberVo> getMemberPage(@RequestBody GetPageReq req) {
    GetPageQuery query = new GetPageQuery(req.pageable());
    Page<MemberDto> page = memberSvc.getMemberPage(query);

    Page<MemberVo> voPage = Page.empty();

    if (BooleanUtils.isFalse(page.isEmpty())) {
      List<MemberVo> vos = page.getContent().stream().map(dto -> {
        MemberVo vo = new MemberVo();
        BeanUtils.copyProperties(dto, vo);
        return vo;
      }).toList();
      voPage = PageableExecutionUtils.getPage(vos, page.getPageable(), page::getTotalElements);
    }

    return voPage;
  }

  @PostMapping("/getMember")
  public MemberVo getMember(@RequestBody GetReq req) {
    GetQuery query = new GetQuery(req.getId());

    MemberDto dto = memberSvc.getMember(query);

    return Optional.ofNullable(dto).map(d -> {
      MemberVo vo = new MemberVo();
      BeanUtils.copyProperties(d, vo);
      return vo;
    }).orElse(null);
  }

  @PostMapping("/register")
  public boolean register(@RequestBody RegisterReq req) {
    RegisterCommand command = new RegisterCommand();
    BeanUtils.copyProperties(req, command);
    return memberSvc.register(command);
  }

  @PostMapping("/unRegister")
  public boolean unRegister(@RequestBody UnRegisterReq req) {
    UnRegisterCommand command = new UnRegisterCommand();
    BeanUtils.copyProperties(req, command);
    return memberSvc.unRegister(command);
  }

  @PostMapping("/edit")
  public boolean edit(@RequestBody EditReq req) {
    EditCommand command = new EditCommand();
    BeanUtils.copyProperties(req, command);
    return memberSvc.edit(command);
  }
}
