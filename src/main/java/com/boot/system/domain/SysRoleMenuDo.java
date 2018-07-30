package com.boot.system.domain;

import java.io.Serializable;
import java.util.List;

public class SysRoleMenuDo implements Serializable {
    private Long id;
    private Long roleId;
    private List<Long> menuId;

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

    public List<Long> getMenuId() {
        return menuId;
    }

    public void setMenuId(List<Long> menuId) {
        this.menuId = menuId;
    }
}
