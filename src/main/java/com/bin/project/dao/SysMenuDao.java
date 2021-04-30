package com.bin.project.dao;

import com.bin.project.dto.SysMenuParam;
import com.bin.project.pojo.SysMenu;
import com.github.pagehelper.Page;

import java.util.List;

public interface SysMenuDao {

    /**
     * 用于封装菜单2
     * @return
     */
    List<SysMenuParam> getUserMenuInfo(String username);

    /**
     * 用于封装菜单1
     * @return
     */
    List<SysMenuParam> getUserMenuInfoOne(String username);

    /**
     * 用于封装菜单0
     * @return
     */
    List<SysMenuParam> getUserMenuInfoZero(String username);

    /**
     * 构建Tree 0级目录
     * @return
     */
    List<SysMenuParam> findMenuLevelZero();
    /**
     * 构建Tree 1级目录
     * @return
     */
    List<SysMenuParam> findMenuLevelOne(Long id);

    /**
     * 根据角色ID查询对应的menu
     * @param id
     * @return
     */
    List<SysMenu> findMenuListByRoleId(Long id);

    /**
     * 分页查询
     * @param queryString
     * @return
     */
    Page<SysMenu> findMenuList(String queryString);

    /**
     * 信息回显
     * @param id
     * @return
     */
    SysMenu findMenuById(Long id);

    /**
     * 更新菜单
     * @param menu
     */
    void updateMenu(SysMenu menu);

    /**
     * 新增菜单
     * @param sysMenu
     */
    void addMenu(SysMenu sysMenu);
}
