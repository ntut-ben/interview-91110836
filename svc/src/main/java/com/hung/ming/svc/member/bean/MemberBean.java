package com.hung.ming.svc.member.bean;


import com.hung.ming.repo.member.entity.Member;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MemberBean extends Member {

}
