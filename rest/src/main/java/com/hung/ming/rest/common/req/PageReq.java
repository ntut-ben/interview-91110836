package com.hung.ming.rest.common.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageReq {

  @Schema(description = "分頁", example = "0")
  private int page;
  @Schema(description = " 資料筆數", example = "10")
  private int size;

  public Pageable pageable() {
    Pageable pageable = Pageable.unpaged();

    if (ObjectUtils.allNotNull(page, size)) {
      pageable = PageRequest.of(page, size);
    }

    return pageable;
  }
}
