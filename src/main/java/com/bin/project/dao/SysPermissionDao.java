package com.bin.project.dao;

import com.bin.project.dto.SysPermissionParam;
import com.bin.project.pojo.SysPermission;
import com.github.pagehelper.Page;

import java.util.List;

public interface SysPermissionDao {

    /**
     * 查询所有权限
     * @return
     */
    List<SysPermission> findPermListInfo();

    /**
     * Tree树形控件 内层
     * @return
     */
    List<SysPermissionParam> findPermListTreeInner(Long pid);

    /**
     * Tree树形控件 外层
     * @return
     */
    List<SysPermissionParam> findPermListTreeOuter();

    /**
     * 根据角色ID查询对应权限
     * @param id
     * @return
     */
    List<SysPermission> findPermListByRoleId(Long id);

    /**
     * 分页查询
     * @param queryString
     * @return
     */
    Page<SysPermission> findPermList(String queryString);

    /**
     * 信息回显
     * @param id
     * @return
     */
    SysPermission findPermById(Long id);

    /**
     * 新增权限
     * @param sysPermission
     */
    void addPerm(SysPermission sysPermission);

    /**
     * 更新权限
     * @param sysPermission
     */
    void updatePerm(SysPermission sysPermission);

}
