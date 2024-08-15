package com.hung.ming.svc.order.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetMemberOrderStatisticsCommand {
  private int count;
}
