package com.hung.ming.rest.member;

import com.github.pagehelper.PageInfo;
import com.hung.ming.repo.util.PropertyUtilsProxy;
import com.hung.ming.rest.member.req.GetPageReq;
import com.hung.ming.rest.member.req.GetReq;
import com.hung.ming.rest.member.req.RegisterReq;
import com.hung.ming.rest.member.resp.MemberVo;
import com.hung.ming.svc.member.IMemberSvc;
import com.hung.ming.svc.member.bean.MemberBean;
import com.hung.ming.svc.member.command.RegisterCommand;
import com.hung.ming.svc.member.query.GetPageQuery;
import com.hung.ming.svc.member.query.GetQuery;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Log4j2
public class MemberCtrl {

  private final IMemberSvc memberSvc;

  @Operation(description = "取得會員資料分頁")
  @GetMapping("/members")
  public PageInfo<MemberVo> getMemberPage(GetPageReq req) {
    GetPageQuery query = new GetPageQuery(req.pageable());
    PageInfo<MemberBean> page = memberSvc.getMemberPage(query);

    PageInfo<MemberVo> voPage = PageInfo.emptyPageInfo();

    if (page.hasContent()) {
      voPage = page.convert(dto -> {
        MemberVo vo = new MemberVo();
        PropertyUtilsProxy.copyProperties(vo, dto);
        return vo;
      });
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

  //
  //  @Operation(description = "刪除會員")
  //  @DeleteMapping("/member")
  //  public boolean unRegister(UnRegisterReq req) {
  //    UnRegisterCommand command = new UnRegisterCommand();
  //    PropertyUtilsProxy.copyProperties(command, req);
  //    return memberSvc.unRegister(command);
  //  }
  //
  //  @Operation(description = "會員資料修改")
  //  @PatchMapping("/member")
  //  public boolean edit(EditReq req) {
  //    EditCommand command = new EditCommand();
  //    PropertyUtilsProxy.copyProperties(command, req);
  //    return memberSvc.edit(command);
  //  }
}
