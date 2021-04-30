package com.bin.project.dao;

import com.bin.project.pojo.SysRoleMenu;

import java.util.List;

public interface SysRoleMenuDao {


    /**
     * 删除原有的对应关系
     * @param id
     */
    void deleteRoleMenuRelation(Long id);

    /**
     * 重新建立对应关系
     * @param list
     */
    void insertList(List<SysRoleMenu> list);
}
