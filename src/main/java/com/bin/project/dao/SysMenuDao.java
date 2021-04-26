package com.bin.project.dao;

import com.bin.project.dto.SysMenuParam;

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
}
