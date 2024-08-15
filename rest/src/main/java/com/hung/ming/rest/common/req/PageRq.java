package com.hung.ming.rest.common.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRq {

  /** 頁碼 */
  private int page;
  /** 資料筆數 */
  private int size;

  public Pageable pageable() {
    Pageable pageable = Pageable.unpaged();

    if (ObjectUtils.allNotNull(page, size)) {
      pageable = PageRequest.of(page, size);
    }

    return pageable;
  }
}
