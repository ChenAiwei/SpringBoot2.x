package com.boot.system.domain;

import java.io.Serializable;
import java.util.List;

public class SysUserRoleDo implements Serializable {
    private Long id;
    private Long roleId;
    private List<Long> userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public List<Long> getUserId() {
        return userId;
    }

    public void setUserId(List<Long> userId) {
        this.userId = userId;
    }
}
