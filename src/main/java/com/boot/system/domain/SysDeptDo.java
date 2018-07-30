package com.boot.system.domain;

import java.io.Serializable;
import java.util.List;

public class SysDeptDo implements Serializable {
        private Long deptId;
        private  Long parentId;
        private String name;
        private List<Integer> orderNum;

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(List<Integer> orderNum) {
        this.orderNum = orderNum;
    }
}
