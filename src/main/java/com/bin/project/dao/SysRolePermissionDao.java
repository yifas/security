package com.bin.project.dao;

import com.bin.project.pojo.SysRolePermission;

import java.util.List;

public interface SysRolePermissionDao {

    /**
     * 根据RoleId 删除对应关系
     * @param id
     */
    void deleteRolePermRelation(Long id);

    /**
     * 建立新的关系表
     * @param list
     */
    void insertList(List<SysRolePermission> list);
}
