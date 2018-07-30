package com.boot.system.service;

import java.util.Set;

public interface SysMenuService {
    Set<String> listPerms(Long userId);
}
