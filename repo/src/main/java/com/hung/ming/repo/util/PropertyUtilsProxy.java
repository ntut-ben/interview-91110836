package com.hung.ming.repo.util;

import lombok.SneakyThrows;

public class PropertyUtilsProxy {
  @SneakyThrows
  public static void copyProperties(Object dest, Object orig) {
    org.apache.commons.beanutils.PropertyUtils.copyProperties(dest, orig);
  }
}
