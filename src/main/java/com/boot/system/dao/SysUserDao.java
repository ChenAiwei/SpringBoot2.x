package com.boot.system.dao;

import com.boot.system.domain.SysUserDo;

import java.util.List;
import java.util.Map;

public interface SysUserDao {
    List<SysUserDo> list(Map<String,Object> map);
}
