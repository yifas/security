package com.bin.project.service;

import com.bin.common.PageQueryBean;
import com.bin.common.PageResult;
import com.bin.project.dto.SysMenuParam;
import com.bin.project.pojo.SysMenu;

import java.util.List;

public interface SysMenuService {
    /**
     * 用于封装菜单
     * @return
     */
    List<SysMenuParam> getUserMenuInfo();

    /**
     * 用于构建Tree数据
     * @return
     */
    List<SysMenuParam> findMenuListTree();

    /**
     * 根据角色ID查询对应的menu
     * @param id
     * @return
     */
    List<SysMenu> findMenuListByRoleId(Long id);

    /**
     * 更新角色对应菜单
     * @param id
     * @param menuIds
     */
    void updateRoleMenu(Long id, List<Long> menuIds);

    /**
     *  分页查询
     * @param pageQueryBean
     * @return
     */
    PageResult findMenuList(PageQueryBean pageQueryBean);

    /**
     *  信息回显
     * @param id
     * @return
     */
    SysMenu findMenuById(Long id);

    /**
     * 更新菜单
     * @param id
     * @param sysMenu
     */
    void updateMenu(Long id, SysMenu sysMenu);

    /**
     * 新增菜单
     * @param sysMenu
     */
    void addMenu(SysMenu sysMenu);
}
