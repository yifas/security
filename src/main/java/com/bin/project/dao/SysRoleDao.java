package com.bin.project.dao;

import com.bin.project.pojo.SysRole;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleDao {

    /**
     * 查询所有角色
     * @return
     */
    Page<SysRole> roleList(String queryString);

    /**
     * 获取当前用户的角色
     * @param username
     * @return
     */
    List<SysRole> findRoleAlreadyLogin(@Param("username") String username);

    /**
     * 根据id查询角色
     * @param id
     * @return
     */
    List<SysRole> findRoleById(@Param("id")Long id);

    /**
     * 用于修改角色信息
     * @param id
     * @return
     */
    SysRole findRoleByRoleId(Long id);

    /**
     * 更新角色信息
     * @param sysRole
     */
    void updateRoleInfo(SysRole sysRole);

    /**
     * crud中查询所有
     * @return
     */
    List<SysRole> findRoleList();

    /**
     * 新增角色
     * @param sysRole
     * @return
     */
    void addRole(SysRole sysRole);
}
