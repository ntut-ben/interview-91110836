package com.hung.ming.rest.member;

import com.hung.ming.repo.util.PropertyUtilsProxy;
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
import com.hung.ming.svc.member.bean.MemberBean;
import com.hung.ming.svc.member.query.GetPageQuery;
import com.hung.ming.svc.member.query.GetQuery;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
  @GetMapping("/members")
  public Page<MemberVo> getMemberPage(GetPageReq req) {
    GetPageQuery query = new GetPageQuery(req.pageable());
    Page<MemberBean> page = memberSvc.getMemberPage(query);

    Page<MemberVo> voPage = Page.empty();

    if (BooleanUtils.isFalse(page.isEmpty())) {
      List<MemberVo> vos = page.getContent().stream().map(dto -> {
        MemberVo vo = new MemberVo();
        PropertyUtilsProxy.copyProperties(vo, dto);
        return vo;
      }).toList();
      voPage = PageableExecutionUtils.getPage(vos, page.getPageable(), page::getTotalElements);
    }

    return voPage;
  }

  @Operation(description = "取得會員資料")
  @GetMapping("/member")
  public MemberVo getMember(GetReq req) {
    GetQuery query = new GetQuery(req.getId());

    MemberBean dto = memberSvc.getMember(query);

    return Optional.ofNullable(dto).map(d -> {
      MemberVo vo = new MemberVo();
      PropertyUtilsProxy.copyProperties(vo, d);
      return vo;
    }).orElse(null);
  }

  @Operation(description = "註冊會員")
  @PostMapping("/member")
  public boolean register(@RequestBody RegisterReq req) {
    RegisterCommand command = new RegisterCommand();
    PropertyUtilsProxy.copyProperties(command, req);
    return memberSvc.register(command);
  }

  @Operation(description = "刪除會員")
  @DeleteMapping("/member")
  public boolean unRegister(UnRegisterReq req) {
    UnRegisterCommand command = new UnRegisterCommand();
    PropertyUtilsProxy.copyProperties(command, req);
    return memberSvc.unRegister(command);
  }

  @Operation(description = "會員資料修改")
  @PatchMapping("/member")
  public boolean edit(EditReq req) {
    EditCommand command = new EditCommand();
    PropertyUtilsProxy.copyProperties(command, req);
    return memberSvc.edit(command);
  }
}
