package com.hung.ming.svc.member.dto;


import com.hung.ming.repo.entity.Member;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MemberDto extends Member {

}
